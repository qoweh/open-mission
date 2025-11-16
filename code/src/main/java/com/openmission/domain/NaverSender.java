package com.openmission.domain;

public class NaverSender extends Sender{
    protected NaverSender(String senderMail) {
        super(senderMail);
        Sender.properties.put("mail.smtp.host", "smtp.naver.com");
        Sender.properties.put("mail.smtp.port", "587");
        Sender.properties.put("mail.smtp.ssl.trust", "smtp.naver.com");
    }
}
