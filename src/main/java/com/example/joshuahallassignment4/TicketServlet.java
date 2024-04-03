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
        // dad stuff remove later
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Ticket support</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<form action\"MyServlet\" method=\"post\">");

        out.println("<label for>\"What do you want to do?\"</label>");

//        out.println("<a href=\"this.showTicketForm(response)\">create</a>");
//        out.println("<a href=\"this.viewTicket(request, response)\">view</a>");
//        out.println("<a href=\"this.downloadAttachment (request, response)\">download</a>");
//        out.println("<a href=\"this.viewTicket(request, response)\">list</a>");
        out.println("<ul>");
        out.println("<li>list</li>");
        out.println("<li>create</li>");
        out.println("<li>view</li>");
        out.println("<li>download</li>");
        out.println("</ul>");

        out.println("<input type=\"text\" id=\"choice\" name=\"choice\">");
        out.println("<button type=\"submit\">Submit</button>");
        out.println("</form>");

        out.println("");
        out.println("</body>");
        out.println("</html>");
        // dad stuff remove later

        String action = request.getParameter("action");
        if (action == null)
            action = "create";
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
            case "list":
                this.listTickets(response);
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

    private void showTicketForm(HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Create Ticket</h1>");
        out.println("form action='ticket-servlet' method='post' enctype='multipart/form-data'>");
        out.println("Customer Name: <input type='text' name='customerName'><br>");
        out.println("Subject: <input type='text' name='subject'><br>");
        out.println("Body: <textarea name='body'></textarea><br>");
        out.println("Attachment: <input type='file' name='file1'><br>");
        out.println("<input type='hidden' name='action' value='create'>");
        out.println("<input type='submit' value='Submit'>");
        out.println("</form>");
        out.println("</body></html>");
    }

    private void viewTicket(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        int ticketId = Integer.parseInt(request.getParameter("ticketID"));
        Ticket ticket = getTicket(ticketId);
        if (ticket != null) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>Ticket Details</h1>");
            out.println("<p>Customer Name: " + ticket.getCustomername() + "</p>");
            out.println("<p>Subject: " + ticket.getSubject() + "</p>");
            out.println("<p>Body: " + ticket.getBody() + "</p>");
            out.println("<p>Attachments:</p>");
            for (Attachment attachment : ticket.getAttachments()) {
                out.println("<p>" + attachment.getName() + "</p>");
                out.println("<a href='ticket-servlet?action=download&ticketID=" + ticketId + "&attachmentIndex=" + ticket.getAttachments().indexOf(attachment) + "'>Download</a>");
            }
            out.println("</body></html>");
        } else {
            response.sendRedirect("ticket-servlet");
        }
    }

    private void downloadAttachment(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException{
        int ticketId = Integer.parseInt(request.getParameter("ticketID"));
        int attachmentIndex = Integer.parseInt(request.getParameter("attachmentIndex"));

        Ticket ticket = ticketDatabase.get(ticketId);
        if (ticket != null && attachmentIndex >= 0 && attachmentIndex < ticket.getAttachments().size()) {
            Attachment attachment = ticket.getAttachments().get(attachmentIndex);
            if (attachment != null) {
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + attachment.getName() + "\"");
                try (OutputStream out = response.getOutputStream()) {
                    out.write(attachment.getContents());
                }
            } else {
                response.sendRedirect("ticket-servlet");
            }
        } else {
            response.sendRedirect("ticket-servlet");
        }
    }

    private void listTickets(HttpServletResponse response) throws
            ServletException, IOException{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>List of Tickets</h1>");
        out.println("<ul>");
        for (Map.Entry<Integer, Ticket> entry : ticketDatabase.entrySet()) {
            out.println("<li><a href='ticket-servlet?action=view&ticketID=" + entry.getKey() + "'>Ticket #" + entry.getKey() + "</a></li>");
        }
        out.println("</ul>");
        out.println("</body></html>");
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

    private Ticket getTicket(int ticketId) {
        return ticketDatabase.get(ticketId);
    }

}