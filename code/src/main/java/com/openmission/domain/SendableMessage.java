package com.openmission.domain;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;

public class SendableMessage {
    private final Message _message;

    public SendableMessage(String title, String content, Message message, Priority priority) throws MessagingException {
        _message = setUpMessage(title, content, message, priority);
    }

    private Message setUpMessage(String title, String content, Message message, Priority priority) throws MessagingException {
        message.setSubject(title);
        message.setContent(content, "text/html; charset=utf-8");
        message.setHeader("X-Priority", priority.name());
        return message;
    }

    public Message getMessage() {
        return _message;
    }
}
