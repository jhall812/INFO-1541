package joshuahallassignment4.entity;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "attachments")
public class Attachment implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long ticketId;
    private String name;
    @Lob
    private byte[] contents;


    @Column(name = "id")
    public long getId(){return id;}

    public void setId(long id){this.id = id;}

    @Column(name = "ticket_id")
    public long getTicketId(){return ticketId;}

    public void setTicketId(long ticketId){this.ticketId = ticketId;}

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public byte[] getContents(){
        return contents;
    }

    public void setContents(byte[] contents){
        this.contents= contents;
    }

    @Override
    public String toString() {
        return "Image{" +
                "name='" + name + '\'' +
                ", contents=" + Arrays.toString(contents) +
                '}';
    }

    public int size() {
        return 0;
    }
}
