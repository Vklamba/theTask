package com.example.theTask;

public class ChatLog {
    private String message;
    private Long timestamp;
    private Boolean isSent;
    private String messageID;
    private String user;

    public ChatLog() {}

    public ChatLog(String message, Long timestamp, Boolean isSent, String messageID, String user) {
        this.message = message;
        this.timestamp = timestamp;
        this.isSent = isSent;
        this.messageID = messageID;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getIsSent() {
        return isSent;
    }

    public void setIsSent(Boolean isSent) {
        this.isSent = isSent;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}

