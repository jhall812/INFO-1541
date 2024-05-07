package joshuahallassignment4.Site;

import joshuahallassignment4.entity.TicketEntity;
import jakarta.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultTicketService implements TicketService {

    @Inject TicketRepository ticketRepo;

    @Inject AttachmentRespository attachmentRepo;

    @Override
    @Transactional
    public List<Ticket> getAllTickets() {
        List<Ticket> list = new ArrayList<>();
        ticketRepo.getAll().forEach(e -> list.add(this.convert(e)));
        return list;
    }

    @Override
    public Ticket getTicket(long id) {
        TicketEntity entity = ticketRepo.get(id);
        return entity == null ? null : this.convert(entity);
    }

    private Ticket convert(TicketEntity entity) {
        Ticket ticket = new Ticket();
        ticket.setCustomerName(entity.getCustomerName());
        ticket.setSubject(entity.getSubject());
        ticket.setBody(entity.getBody());
        // look up the image in repo
        ticket.setAttachments(attachmentRepo.getByTicketId(entity.getTicketId()));
        // do i need to check if image is null?

        return ticket;
    }

    @Override
    @Transactional
    public void save(Ticket ticket) {
        // convert from blog -> blogentity
        TicketEntity entity = new TicketEntity();
        entity.setCustomerName(ticket.getCustomerName());
        entity.setSubject(ticket.getSubject());
        entity.setBody(ticket.getBody());

        if (ticket.getId() < 1) { // doesn't have an id which means not updating
            // add to the repo
            ticketRepo.add(entity);
            ticket.setId(entity.getTicketId()); // get the id from the entity to use in the Controller to actually view the blog
            // add image?
            if (ticket.hasImage()) {
                ticket.getAttachments().setTicketId(entity.getTicketId());
                attachmentRepo.add(ticket.getAttachments());
            }
        }
        else { // just editing the blog so update it on the DB
            ticketRepo.update(entity);
        }

    }

    @Override
    @Transactional
    public void deleteTicket(long id) {
        ticketRepo.deleteById(id);
        attachmentRepo.getByTicketId(id);
    }
}
