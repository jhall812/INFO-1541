package joshuahallassignment4.entity;

import jakarta.persistence.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.userdetails.User;

import javax.security.auth.Subject;
import java.io.Serializable;
import java.security.Principal;

@Entity
@Table(name = "user_principal")
public class UserPrincipal implements Principal, Serializable{
   private static final long serialVersionUID = 1L;
   private static final String SESSION_ATTRIBUTE_KEY = "com.example.joshuahallassignment4.userprincipal.principal";
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "user_id")
   private long id;
   @Column(name = "username")
   private String username;
   @Column(name = "hashedpassword")
   private byte[] password;

   @Id
   public long getId(){return id;}

   public void setId(long id){this.id = id;}

   @Basic
   public String getUsername(){return username;}

   public void setUsername(String username){this.username = username;}

   @Basic
   public byte[] getPassword(){return password;}

   public void setPassword(byte[] password){this.password = password;}

   @Override
   public int hashCode() {
      return username.hashCode();
   }

   @Override
   public String toString() {
      return username;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof UserPrincipal) {
         UserPrincipal user = (UserPrincipal) obj;
         return user.getUsername().equals(username);
      }
      return false;
   }

   public static Principal getPrincipal(HttpSession session) {
      return session == null ? null : (Principal)session.getAttribute(SESSION_ATTRIBUTE_KEY);
   }

   public static void setPrincipal(HttpSession session, Principal principal) {
      session.setAttribute(SESSION_ATTRIBUTE_KEY, principal);
   }

   @Override
   @Transient
   public String getName() {
      return username;
   }

   @Override
   public boolean implies(Subject subject) {
      return Principal.super.implies(subject);
   }
}

