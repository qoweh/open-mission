package com.openmission.domain;

import com.openmission.domain.sender.Sender;
import jakarta.mail.Authenticator;

public class GoogleSender extends Sender {
    protected GoogleSender(String senderMail, Authenticator authenticator) {
        super(senderMail, authenticator);
        _properties.put("mail.smtp.host", "smtp.gmail.com");
        _properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    }
}
