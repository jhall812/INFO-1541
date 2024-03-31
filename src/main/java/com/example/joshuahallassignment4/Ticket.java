package com.example.joshuahallassignment4;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Ticket {
    private String customername;
    private String subject;
    private String body;
    private List<Attachment> attachments;

    public Ticket(){
        this.attachments = new ArrayList<>();
    }

    public Ticket(String customername, String subject, String body){
        this.customername = customername;
        this.subject = subject;
        this.body = body;
        this.attachments = new ArrayList<>();
    }

    public String getCustomername(){
        return customername;
    }

    public void setCustomername(String customername){
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
