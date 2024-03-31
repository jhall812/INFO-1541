package com.example.joshuahallassignment4;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
@MultipartConfig(fileSizeThreshold = 5_242_880, maxFileSize = 20_971_520L, maxRequestSize = 41_943_040L)
public class TicketServlet extends HttpServlet {
    private volatile int TICKET_ID_SEQUENCE = 1;
    private Map<Integer, Ticket> ticketDatabase = new LinkedHashMap<>();

    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null)
            action = "list";
        switch (action){
            case "create":
                this.showTicketForm(response);
                break;
            case "view":
                this.viewTicket(request, response);
                break;
            case "download":
                this.downloadAttachment (request, response);
                break;
            case "download":
            default:
                this.listTickets(response);
                break;
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
        ticket.setCustomername(request.getParameter("customerName"));
        ticket.setSubject(request.getParameter("subject"));
        ticket.setBody(request.getParameter("body"));

        Part filepart = request.getPart("file1");
        if (filepart != null){
            Attachment attachment = this.processAttachment(filepart);
            if (attachment != null)
                ticket.addAttachment(attachment);
        }

        int id;
        synchronized (this){
            id = this.TICKET_ID_SEQUENCE++;
            this.ticketDatabase.put(id, ticket);
        }

        response.sendRedirect("tickets?action=view&ticketID=" + id);
    }

    private Attachment processAttachment (Part filePart) throws IOException{
        InputStream inputStream = filePart.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    }


}