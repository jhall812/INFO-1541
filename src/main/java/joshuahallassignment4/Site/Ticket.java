package joshuahallassignment4.Site;

import joshuahallassignment4.entity.Attachment;

import java.util.List;
import java.util.ArrayList;

public class Ticket {
    private long id;
    private String customername;
    private String subject;
    private String body;
    private Attachment attachment;

    public Ticket(){
        this.customername = "";
        this.subject = "";
        this.body = "";
    }

    public Ticket(String customername, String subject, String body){
        this.customername = customername;
        this.subject = subject;
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerName(){
        return customername;
    }

    public void setCustomerName(String customername){
        this.customername = customername;
    }

    public String getSubject(){
        return subject;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public String getBody(){
        return body;
    }

    public void setBody(String body){
        this.body = body;
    }

    public Attachment getAttachments(){
        return attachment;
    }

    public void setAttachments(Attachment attachment){
        this.attachment = attachment;
    }

    public void addAttachment(Attachment attachment){
        this.attachment = attachment;
    }

    public int getNumberOfAttachments(){
        return attachment != null ? 1 : 0;
    }

    public Attachment getAllAttachments(){
        return attachment;
    }

    public boolean hasImage() {
        return attachment != null && Attachment.getName() > 0 && Attachment.getContents() > 0;
    }
}
