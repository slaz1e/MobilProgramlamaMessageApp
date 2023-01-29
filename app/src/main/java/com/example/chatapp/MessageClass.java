package com.example.chatapp;

public class MessageClass {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String message,name;

    public MessageClass(String name, String message ) {
        this.message = message;
        this.name = name;
    }
}
