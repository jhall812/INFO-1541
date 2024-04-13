package com.example.joshuahallassignment4;

import java.util.Arrays;

public class Attachment {
    private String name;
    private byte[] contents;

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public byte[] getContents() {
        return contents;
    }

    public void setContents(byte[] contents){
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "Image{" +
                "name='" + name + '\'' +
                ", contents=" + Arrays.toString(contents) +
                '}';
    }
}
