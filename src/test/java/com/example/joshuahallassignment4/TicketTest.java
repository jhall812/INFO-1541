package com.example.joshuahallassignment4;

import com.example.joshuahallassignment4.site.Attachment;
import com.example.joshuahallassignment4.site.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TicketTest {
    private Ticket ticket;

    @BeforeEach
    public void setUp() {
        ticket = new Ticket();
    }

    @Test
    public void testTicketConstructorNoParms()
    {
        Ticket testTicket = new Ticket();
        assertNotNull(testTicket);
        assertEquals(testTicket.getCustomerName(), "");
    }

    @Test
    public void testTicketConstructorWithParms()
    {
        ArrayList<Attachment> attachments = new ArrayList<>();
        Attachment bat = new Attachment();
        attachments.add(bat);
        Ticket testTicket = new Ticket("Jim Gordon", "Batman repair", "Batmobile lost a wheel", attachments);
        assertNotNull(testTicket);
        assertEquals(testTicket.getCustomerName(), "Jim Gordon");
    }
    @Test
    public void testCustomerName() {
        String customerName = "John Doe";
        ticket.setCustomerName(customerName);
        assertEquals(customerName, ticket.getCustomerName());
    }

    @Test
    public void testSubject() {
        String subject = "Test Subject";
        ticket.setSubject(subject);
        assertEquals(subject, ticket.getSubject());
    }

    @Test
    public void testBody() {
        String body = "Test Body";
        ticket.setBody(body);
        assertEquals(body, ticket.getBody());
    }

    @Test
    public void testAttachments() {
        List<Attachment> attachments = new ArrayList<>();
        Attachment attachment = new Attachment();
        attachments.add(attachment);
        ticket.setAttachments(attachments);
        assertEquals(attachments, ticket.getAttachments());
        assertEquals(1, ticket.getNumberOfAttachments());
    }

    @Test
    public void testGetIndividualAttachment(){
        List<Attachment> attachments = new ArrayList<>();
        Attachment attachment = new Attachment();
        attachments.add(attachment);
        ticket.setAttachments(attachments);
        assertEquals(attachment, ticket.getIndividualAttachment(1));
        assertEquals(1, ticket.getNumberOfAttachments());
    }

    @Test
    public void testAddAttachment() {
        Attachment attachment = new Attachment();
        ticket.addAttachment(attachment);
        assertEquals(1, ticket.getNumberOfAttachments());
    }

    @Test
    public void testNumberOfAttachments() {
        assertEquals(0, ticket.getNumberOfAttachments());
    }

    @Test
    public void testGetAllAttachments() {
        List<Attachment> attachments = new ArrayList<>();
        Attachment attachment1 = new Attachment();
        Attachment attachment2 = new Attachment();
        attachments.add(attachment1);
        attachments.add(attachment2);
        ticket.setAttachments(attachments);
        assertEquals(attachments, ticket.getAllAttachments());
    }
}