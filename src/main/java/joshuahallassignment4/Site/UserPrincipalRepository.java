package joshuahallassignment4.Site;

import joshuahallassignment4.entity.UserPrincipal;


public interface UserPrincipalRepository extends GenericRepository<Long, UserPrincipal>{

    UserPrincipal getByUsername(String username);
}
