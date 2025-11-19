package com.openmission.domain;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;

public class Mail {
    private final SendableMessage _sendableMessage;

    private Mail(String title, String content, Message message) throws MessagingException {
        _sendableMessage = new SendableMessage(title, content, message);
    }

    public static Mail of(String title, String content, Session session) throws MessagingException {
        validate(title, content);
        return new Mail(title, content, new MimeMessage(session));
    }

    private static void validate(String title, String content) {
    }

    public Message getMessage() {
        return _sendableMessage.getMessage();
    }
}
