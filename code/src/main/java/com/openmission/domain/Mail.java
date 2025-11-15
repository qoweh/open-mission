package com.openmission.domain;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;

public class Mail {
    private final String title;
    private final String content;
    private final Message message;

    private Mail(String title, String content, Message message) {
        this.title = title;
        this.content = content;
        this.message = message;
    }

    public static Mail of(String title, String content, Session session) {
        validate(title, content);
        return new Mail(title, content, new MimeMessage(session));
    }

    private static void validate(String title, String content) {
    }

    public Message setUpMessage() throws MessagingException {
        message.setSubject(title);
        message.setContent(content, "text/html; charset=utf-8");

        return message;
    }
}
