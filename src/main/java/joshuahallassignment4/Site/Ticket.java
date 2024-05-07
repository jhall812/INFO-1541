package joshuahallassignment4.Site;

import java.util.List;
import java.util.ArrayList;

public class Ticket {
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

    public List<Attachment> getAttachments(){
        return attachments;
    }

    public Attachment getIndividualAttachment(int pos)
    {
        if (this.attachments.size() >= pos-1)
            return this.attachments.get(pos-1);
        else
            return null;
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
}
