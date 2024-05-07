package joshuahallassignment4.Site;

import joshuahallassignment4.entity.Attachment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DefaultAttachmentRepository extends GenericJpaRepository<Long, Attachment> implements AttachmentRespository {

    @Override
    public List<Attachment> getByTicketId(Long ticketId) {
        try {
            return this.entityManager.createQuery("SELECT i FROM Attachment i WHERE i.ticketId = :id", Attachment.class).setParameter("id", ticketId).getSingleResult();
        }
        catch(Exception e) {
            return null;
        }
    }
}
