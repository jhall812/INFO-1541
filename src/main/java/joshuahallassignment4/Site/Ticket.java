package joshuahallassignment4.Site;

import joshuahallassignment4.entity.Attachment;

import java.util.List;
import java.util.ArrayList;

public class Ticket {
    private long id;
    private String customername;
    private String subject;
    private String body;
    private List<Attachment> attachments;

    public Ticket(){
        this.customername = "";
        this.subject = "";
        this.body = "";
        this.attachments = new ArrayList<>();
    }

    public Ticket(String customername, String subject, String body, ArrayList<Attachment> attachments){
        this.customername = customername;
        this.subject = subject;
        this.body = body;
        if (attachments == null)
            this.attachments = new ArrayList<>();
        else
            this.attachments = attachments;
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
        return (Attachment) attachments;
    }

    public void setAttachments(List<Attachment> attachments){
        this.attachments = attachments;
    }

    public void addAttachment(Attachment attachment){
        this.attachments.add(attachment);
    }

    public int getNumberOfAttachments(){
        return attachments.size();
    }

    public List<Attachment> getAllAttachments(){
        return attachments;
    }

    public boolean hasImage() {
        return attachments != null && Attachment.getName().length() > 0 && Attachment.getContents().length > 0;
    }
}
