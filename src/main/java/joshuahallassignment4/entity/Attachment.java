package joshuahallassignment4.entity;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "attachments")
public class Attachment implements Serializable{
    private static final long serialVersionUID = 1L;
    private long id;
    private long ticketId;
    private static String name;
    @Lob
    private static byte[] contents;


    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId(){return id;}

    public void setId(long id){this.id = id;}

    @Basic
    public long getTicketId(){return ticketId;}

    public void setTicketId(long ticketId){this.ticketId = ticketId;}

    @Basic
    public static String getName(){return name;}

    public void setName(String name){this.name = name;}

    @Lob
    public static byte[] getContents(){return contents;}

    public void setContents(byte[] contents){this.contents = contents;}

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
