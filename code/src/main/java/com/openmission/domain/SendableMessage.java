package com.openmission.domain;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;

public class SendableMessage {
    private final Message message;

    public SendableMessage(String title, String content, Message message) throws MessagingException {
        this.message = setUpMessage(title, content, message);
    }

    private Message setUpMessage(String title, String content, Message message) throws MessagingException {
        message.setSubject(title);
        message.setContent(content, "text/html; charset=utf-8");
        return message;
    }

    public Message getMessage() {
        return message;
    }
}
