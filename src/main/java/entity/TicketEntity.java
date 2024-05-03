package entity;

import com.example.joshuahallassignment4.Attachment;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="tickets")
public class TicketEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    private String name;
    private String subject;
    private String body;
    private List<Attachment> attachment;
}
