package com.example.joshuahallassignment4;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ticketServlet", value = "/ticket")
@MultipartConfig(fileSizeThreshold = 5_242_880, maxFileSize = 20_971_520L, maxRequestSize = 41_943_040L)
public class TicketServlet extends HttpServlet {
    private volatile int TICKET_ID_SEQUENCE = 1;
    private final Map<Integer, Ticket> ticketDatabase = new LinkedHashMap<>();

    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null)
            action = "list";
        switch (action){
            case "create" -> showTicketForm(request, response);
            case "view" -> viewTicket(request, response);
            case "download" -> downloadAttachment (request, response);
            case "list" -> listTickets(request, response);
        }
    }

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException{
        String action = request.getParameter("action");
        if (action == null)
            action = "list";
        switch (action){
            case "create":
                this.createTicket(request, response);
                break;
            case "download":
            default:
                response.sendRedirect("tickets");
                break;
        }
    }

    private void createTicket (HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        Ticket ticket = new Ticket();
        ticket.setCustomerName(request.getParameter("customerName"));
        ticket.setSubject(request.getParameter("subject"));
        ticket.setBody(request.getParameter("body"));

        Part filepart = request.getPart("file1");
        if (filepart != null){
            Attachment attachment = this.processAttachment(filepart);
            ticket.addAttachment(attachment);
        }

        int id;
        synchronized (this){
            id = this.TICKET_ID_SEQUENCE++;
            this.ticketDatabase.put(id, ticket);
        }

        response.sendRedirect("ticket?action=view&ticketId=" + id);
    }

    private void showTicketForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ticketForm = "/WEB-INF/jsp/view/ticketForm.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(ticketForm);
        dispatcher.forward(request, response);
    }

    private void viewTicket(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String idString = request.getParameter("ticketId");
        if(idString == null || idString.isEmpty()){
            return;
        }

        Ticket ticket = this.getTicket(idString, response);
        if(ticket == null)
            return;

        request.setAttribute("ticketId", idString);
        request.setAttribute("ticket", ticket);
        request.getRequestDispatcher("/WEB-INF/jsp/view/viewTicket.jsp")
                .forward(request, response);
    }

    private void downloadAttachment(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException{
        String idString = request.getParameter("ticketId");

        Ticket ticket = getTicket(idString, response);

        String name = request.getParameter("image");
        if(name == null){
            response.sendRedirect("ticket?action=view&ticketId=" + idString);
        }

        assert ticket != null;
        Attachment attachment = (Attachment) ticket.getAttachments();
        if(attachment == null){
            response.sendRedirect("ticket?action=view&ticketId=" + idString);
            return;
        }

        response.setHeader("Content-Disposition", "attachment; filename=" + attachment.getName());
        response.setContentType("application/octet-stream");

        ServletOutputStream out = response.getOutputStream();
        out.write(attachment.getContents());

    }

    private void listTickets(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException{
        request.setAttribute("ticketDatabase", ticketDatabase);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/view/listTicket.jsp");
        dispatcher.forward(request, response);
    }

    private Attachment processAttachment (Part filePart) throws IOException{
        InputStream inputStream = filePart.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        int read;
        final byte[] bytes = new byte[1024];

        while ((read = inputStream.read(bytes)) !=  -1){
            outputStream.write(bytes, 0, read);
        }

        Attachment attachment = new Attachment();

        attachment.setName(filePart.getSubmittedFileName());
        attachment.getContents();

        return attachment;
    }

    private Ticket getTicket(String idString, HttpServletResponse response) throws ServletException, IOException {
//        return ticketDatabase.get(ticketId);

        if (idString == null || idString.isEmpty()) {
            response.sendRedirect("blog");
            return null;
        }

        try {
            int id = Integer.parseInt(idString);
            Ticket ticket = ticketDatabase.get(id);
            if (ticket == null) {
                response.sendRedirect("blog");
                return null;
            }
            return ticket;
        }
        catch(Exception e) {
            response.sendRedirect("blog");
            return null;
        }
    }


}