package com.openmission.domain.mail;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;

public class Mail {
    private final SendableMessage sendableMessage;

    private Mail(String title, String content, Message message, Priority priority) throws MessagingException {
        sendableMessage = new SendableMessage(title, content, message, priority);
    }

    public static Mail of(String title, String content, Session session, String priorityString) throws MessagingException {
        Priority priority = Priority.fromSymbol(priorityString);
        return new Mail(title, content, new MimeMessage(session), priority);
    }

    public String getTitle() {
        return sendableMessage.getTitle();
    }

    public Message getMessage() {
        return sendableMessage.getMessage();
    }
}
