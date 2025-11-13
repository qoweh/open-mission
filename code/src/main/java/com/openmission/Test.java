package com.openmission;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.mail.Address;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Test {

    private static Dotenv dotenv = Dotenv.load();
    private static final String APP_PASSWORD = dotenv.get("APP_PASSWORD");

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        String sender = "rlaalsdn110@gmail.com";
        String receiver = "sunnybikers@naver.com";
        String title = "테스트 메일입니다.";
        String content = "안녕하세요 ㅎㅇㅎㅇ";
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, APP_PASSWORD);
            }
        });
        Message message = new MimeMessage(session);
        try {
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            message.addRecipients(Message.RecipientType.TO, new InternetAddress[] {new InternetAddress("minu421@naver.com"), new InternetAddress("sunnybikers@naver.com")});
            message.setSubject(title);
            message.setContent(content, "text/html; charset=utf-8");

            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
