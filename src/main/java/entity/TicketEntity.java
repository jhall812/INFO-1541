package entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="tickets")
public class TicketEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    private long ticketId;
    private String customerName;
    private String subject;
    private String body;


    @Id
    @Column(name = "ticketId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getTicketId(){return ticketId;}

    public void setTicketId(long ticketId){this.ticketId = ticketId;}

    @Basic
    public String getCustomerName(){return customerName;}

    public void setCustomerName(String customerName){this.customerName = customerName;}

    @Basic
    public String getSubject(){return subject;}

    public void setSubject(String subject){this.subject = subject;}

    @Basic
    public String getBody(){return body;}

    public void setBody(String body){this.body = body;}


}
