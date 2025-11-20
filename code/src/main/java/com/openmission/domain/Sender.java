package com.openmission.domain;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import java.util.Properties;

public abstract class Sender {
    protected final Properties _properties;
    private final String _mail;
    private final Session _session;

    protected Sender(final String mail, final Authenticator authenticator) {
        _properties = makeProperties();
        _mail = mail;
        _session = Session.getInstance(_properties, authenticator);
    }

    private Properties makeProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");
        return properties;
    }

    public static Sender from(String mail) {
        return MailDomain.createFrom(mail);
    }

    public void send(Mail mail, Receivers receivers) throws MessagingException {
        Message message = mail.getMessage();
        message.setFrom(new InternetAddress(_mail));
        message.setRecipients(Message.RecipientType.TO, receivers.getReceiversAddress());
        message.setRecipients(Message.RecipientType.CC, receivers.getCcReceiversAddress());

        Transport.send(message);
    }

    public String getMail() {
        return _mail;
    }

    public Session getSession() {
        return _session;
    }
}
