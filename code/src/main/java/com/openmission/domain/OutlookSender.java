package com.openmission.domain;

import jakarta.mail.Authenticator;

public class GoogleSender extends Sender {
    protected GoogleSender(String senderMail, Authenticator authenticator) {
        super(senderMail, authenticator);
        _properties.put("mail.smtp.host", "smtp.google.com");
        _properties.put("mail.smtp.port", "465");
        _properties.put("mail.smtp.ssl.trust", "smtp.google.com");
    }
}
