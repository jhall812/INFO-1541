package joshuahallassignment4.Site;


import joshuahallassignment4.entity.Attachment;
import jakarta.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;


@Controller
@RequestMapping("ticket")
public class TicketController {
//    private volatile int TICKET_ID = 1;
//    private Map<Integer, Ticket> ticketDB = new LinkedHashMap<>();

    @Inject TicketService ticketService;
    @RequestMapping(value = {"list", ""})
    public String listTickets(Model model){
        model.addAttribute("ticketDatabase", ticketService.getAllTickets());
        return "listTicket";
    }

    @GetMapping("create")
    public ModelAndView createTicket(){
        return new ModelAndView("ticketForm", "ticket", new TicketForm());
    }

    @PostMapping("create")
    public View createTicket(@ModelAttribute("ticket") TicketForm form) throws IOException {
        Ticket ticket = new Ticket();
        ticket.setCustomerName(form.getCustomerName());
        ticket.setSubject(form.getSubject());
        ticket.setBody(form.getBody());


        MultipartFile file = form.getAttachment();
        if ((file != null && !file.isEmpty())) {
            Attachment image = new Attachment();
            image.setName(file.getOriginalFilename());
            image.setContents(file.getBytes());
            ticket.addAttachment(image);
        }

//        int id;
//        synchronized(this) {
//            id = this.TICKET_ID++;
//            ticketDB.put(id, ticket);
//        }

        ticketService.save(ticket);

        return new RedirectView("view/"+ticket.getId(), true, false);
    }

    @GetMapping("view/{ticketId}")
    public ModelAndView viewTicket(Model model, @PathVariable("ticketId") int ticketId) {
        Ticket ticket = ticketService.getTicket(ticketId);
        if (ticket == null) {
            return new ModelAndView(new RedirectView("ticket/list", true, false));
        }

        // found the blog, so send it to the view
        model.addAttribute("ticketId", ticketId);
        model.addAttribute("ticket", ticket);

        return new ModelAndView("viewTicket");
    }

    @GetMapping("/{ticketId}/image/{image:.+}")
    public View downloadImage(@PathVariable("ticketId")int ticketId, @PathVariable("image") String name) {
        Ticket ticket = ticketService.getTicket(ticketId);
        // no ticket
        if (ticket == null) {
            return new RedirectView("/ticket/list", true, false);
        }

        // make sure there is an image
        joshuahallassignment4.entity.Attachment image =  ticket.getAttachments();
        if (image == null) {
            return new RedirectView("/ticket/list", true, false);
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
