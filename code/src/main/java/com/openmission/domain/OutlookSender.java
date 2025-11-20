package com.openmission.domain;

import com.openmission.domain.sender.Sender;
import jakarta.mail.Authenticator;

public class OutlookSender extends Sender {
    protected OutlookSender(String senderMail, Authenticator authenticator) {
        super(senderMail, authenticator);
        _properties.put("mail.smtp.host", "smtp.office365.com");
        _properties.put("mail.smtp.ssl.trust", "smtp.office365.com");
    }
}
