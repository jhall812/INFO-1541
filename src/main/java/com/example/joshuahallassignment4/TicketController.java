package com.example.joshuahallassignment4;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;


@Controller
@RequestMapping("/ticket")
public class TicketController {
    private volatile int TICKET_ID_SEQUENCE = 1;
    private Map<Integer, Ticket> TicketDB = new LinkedHashMap<>();

    @RequestMapping(value = {"list", ""})
    public String listTickets(Model model){
        model.addAttribute("ticketDatabase", TicketDB);
        return "listTickets";
    }

    @GetMapping("/create")
    public ModelAndView createTicket(){
        return new ModelAndView("ticketForm", "ticket", new TicketForm());
    }

    @PostMapping("/create")
    public View createTicket(@ModelAttribute("ticket") TicketForm form,
                             @RequestParam("attachment") MultipartFile attachment ) throws IOException {
        Ticket ticket = new Ticket();
        ticket.setCustomerName(form.getCustomerName());
        ticket.setSubject(form.getSubject());
        ticket.setBody(form.getBody());

        if(!attachment.isEmpty()){
            Attachment ticketAttachment = processAttachment(attachment);
            ticket.addAttachment(ticketAttachment);
        }

        int id;
        synchronized(this) {
            id = this.TICKET_ID_SEQUENCE++;
            TicketDB.put(id, ticket);
        }

        return new RedirectView("/ticket/view?id=" + id, true, false);
    }

    private Attachment processAttachment(MultipartFile filepart) throws  IOException{
        InputStream inputStream = filepart.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        int read;
        final byte[] bytes = new byte[1024];

        while ((read = inputStream.read(bytes)) !=  -1){
            outputStream.write(bytes, 0, read);
        }

        Attachment attachment = new Attachment();
        attachment.setName(filepart.getOriginalFilename());
        attachment.getContents();
        return attachment;
    }

    @GetMapping("view/{ticketId}")
    public ModelAndView viewPost(Model model, @PathVariable("ticketId")int ticketId) {
        Ticket ticket = TicketDB.get(ticketId);
        if (ticket == null) {
            return new ModelAndView(new RedirectView("ticket/list", true, false));
        }

        // found the blog, so send it to the view
        model.addAttribute("ticketId", ticketId);
        model.addAttribute("ticket", ticket);

        return new ModelAndView("viewBlog");

    }

    @GetMapping("/{ticketId}/image/{image:.+}")
    public View downloadImage(@PathVariable("ticketId")int ticketId, @PathVariable("image") String name) {
        Ticket ticket = TicketDB.get(ticketId);
        // no ticket
        if (ticket == null) {
            return new RedirectView("listTickets", true, false);
        }

        // make sure there is an image
        TicketForm form = new TicketForm();
        Attachment image = (Attachment) form.getAttachment();
        if (image == null) {
            return new RedirectView("listTickets", true, false);
        }

        // otherwise we have an image, lets download
        return new DownloadView(image.getName(), image.getContents());
    }

    public static class TicketForm {
        private String customerName;
        private String subject;
        private String body;
        private MultipartFile attachment;

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public MultipartFile getAttachment() {
            return attachment;
        }

        public void setAttachment(MultipartFile attachment) {
            this.attachment = attachment;
        }
    }
}
