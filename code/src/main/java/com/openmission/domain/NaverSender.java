package com.openmission.domain;

import jakarta.mail.Authenticator;

public class NaverSender extends Sender {
    protected NaverSender(String senderMail, Authenticator authenticator) {
        super(senderMail, authenticator);
        _properties.put("mail.smtp.host", "smtp.naver.com");
        _properties.put("mail.smtp.ssl.trust", "smtp.naver.com");
    }
}
