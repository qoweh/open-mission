package com.openmission.domain;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import java.util.Properties;

public abstract class Sender {
    protected static Properties properties;
    protected static Authenticator authenticator;
    private static String mail;
    private final Session session;

    private static final String DOMAIN_NAVER = "@naver.com";
    private static final String DOMAIN_GOOGLE = "@google.com";
    private static final String EMPTY_STRING = "빈 값입니다.";
    private static final String INVALID_DOMAIN_MAIL = "지원하지 않는 도메인의 이메일입니다.";
    private static final String MAIL_PATTERN = "^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$";
    private static final String productEnvFile = ".env.examples";
    private static final String developEnvFile = ".env";
    private static final Dotenv dotenv = Dotenv.configure().filename(developEnvFile).load();
    private static final String NAVER_APP_PASSWORD = dotenv.get("NAVER_APP_PASSWORD", "EMPTY_VALUE");
    private static final String GOOGLE_APP_PASSWORD = dotenv.get("GOOGLE_APP_PASSWORD", "EMPTY_VALUE");

    protected Sender(String mail) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Sender.properties = properties;
        Sender.mail = mail;

        this.session = Session.getInstance(Sender.properties, Sender.authenticator);
    }


    public static Sender from(String mail) {
        validate(mail);
        if (mail.contains(DOMAIN_NAVER)) {
            authenticator = makeAuthenticator(mail, NAVER_APP_PASSWORD);
            return new NaverSender(mail);
        }
        if (mail.contains(DOMAIN_GOOGLE)) {
            authenticator = makeAuthenticator(mail, GOOGLE_APP_PASSWORD);
            return new GoogleSender(mail);
        }
        throw new IllegalArgumentException(INVALID_DOMAIN_MAIL);
    }

    private static void validate(String mail) {
        if (mail.isBlank()) {
            throw new IllegalArgumentException(EMPTY_STRING);
        }
        if (!mail.matches(MAIL_PATTERN)) {
            throw new IllegalArgumentException(INVALID_DOMAIN_MAIL);
        }
    }

    private static Authenticator makeAuthenticator(String mail, String password) {
        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail, password);
            }
        };
    }

    public Session getSession() {
        return this.session;
    }

    public void send(Mail mail, Receivers receivers) throws MessagingException {
        Message message = mail.setUpMessage();
        message.setFrom(new InternetAddress(Sender.mail));
        message.setRecipients(Message.RecipientType.TO, receivers.getAddresses());

        Transport.send(message);
    }
}
