package joshuahallassignment4.Site;

import joshuahallassignment4.entity.UserPrincipal;

public interface AuthenticationService {
    UserPrincipal authenticate(String username, String password);
    void saveUser(UserPrincipal principal, String newPassword);
}
