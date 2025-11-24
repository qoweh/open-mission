package com.openmission.domain.mail;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;

public class SendableMessage {
    private final String title;
    private final Message message;

    public SendableMessage(String title, String content, Message message, Priority priority) throws MessagingException {
        this.title = title;
        this.message = setUpMessage(title, content, message, priority);
    }

    private Message setUpMessage(String title, String content, Message message, Priority priority) throws MessagingException {
        message.setSubject(title);
        message.setContent(content, "text/html; charset=utf-8");
        message.setHeader("X-Priority", priority.getPriorityValue());
        return message;
    }

    public String getTitle() {
        return title;
    }

    public Message getMessage() {
        return message;
    }
}
