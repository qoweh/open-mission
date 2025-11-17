package com.openmission.domain;

public class DraftMail {
    private Sender sender;
    private Receivers receivers;
    private String title;
    private String content;

    public DraftMail() {
        this.sender = null;
        this.receivers = null;
        this.title = null;
        this.content = null;
        this.mail = null;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }

    public Receivers getReceivers() {
        return receivers;
    }

    public void setReceivers(Receivers receivers) {
        this.receivers = receivers;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Mail mail;


}
