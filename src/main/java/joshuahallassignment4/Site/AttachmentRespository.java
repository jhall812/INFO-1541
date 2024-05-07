package joshuahallassignment4.Site;

import joshuahallassignment4.entity.Attachment;

import java.util.List;

public interface AttachmentRespository extends GenericRepository<Long, Attachment>{

    List<Attachment> getByTicketId(Long ticketId);
}
