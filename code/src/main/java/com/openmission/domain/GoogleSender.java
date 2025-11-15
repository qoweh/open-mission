package com.openmission.domain;

public class GoogleSender extends Sender{
    protected GoogleSender(String senderMail) {
        super(senderMail);
        Sender.properties.put("mail.smtp.host", "smtp.google.com");
        Sender.properties.put("mail.smtp.port", "465");
        Sender.properties.put("mail.smtp.ssl.trust", "smtp.google.com");
    }

    @Override
    public void send(Mail mail, Receiver receiver) {

    }
}
