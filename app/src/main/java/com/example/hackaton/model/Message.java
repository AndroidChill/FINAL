package com.example.hackaton.model;

import java.util.UUID;

public class Message {

    private String id;
    private String author;
    private String text;

    public Message(){}

    public Message(String id, String author, String text) {
        this.id = id;
        this.author = author;
        this.text = text;
    }

    public Message(String author, String text) {
        this.author = author;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
