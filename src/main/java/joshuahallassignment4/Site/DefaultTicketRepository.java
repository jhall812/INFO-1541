package joshuahallassignment4.Site;

import joshuahallassignment4.entity.TicketEntity;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultTicketRepository extends GenericJpaRepository<Long, TicketEntity> implements TicketRepository{
}
