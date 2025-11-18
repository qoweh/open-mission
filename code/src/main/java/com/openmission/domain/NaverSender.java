package com.openmission.domain;

import com.openmission.domain.entity.Sender;
import jakarta.mail.Authenticator;

public class NaverSender extends Sender {
    protected NaverSender(String senderMail, Authenticator authenticator) {
        super(senderMail, authenticator);
        _properties.put("mail.smtp.host", "smtp.naver.com");
        _properties.put("mail.smtp.port", "587");
        _properties.put("mail.smtp.ssl.trust", "smtp.naver.com");
    }
}
