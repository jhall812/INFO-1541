package com.example.joshuahallassignment4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AttachmentTest {
    private Attachment attachment;

    @BeforeEach
    public void setUp() {
        attachment = new Attachment();
    }

    @Test
    public void testGetName() {
        String name = "Test Attachment";
        attachment.setName(name);
        assertEquals(name, attachment.getName());
    }

    @Test
    public void testGetContents() {
        byte[] contents = {1, 2, 3};
        attachment.setContents(contents);
        assertArrayEquals(contents, attachment.getContents());
    }

    @Test
    public void testSetName() {
        String name = "New Attachment Name";
        attachment.setName(name);
        assertEquals(name, attachment.getName());
    }

    @Test
    public void testSetContents() {
        byte[] contents = {4, 5, 6};
        attachment.setContents(contents);
        assertArrayEquals(contents, attachment.getContents());
    }
}