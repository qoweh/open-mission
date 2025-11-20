package com.openmission;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class Test {

    private static final String productEnvFile = ".env.examples";
    private static final String developEnvFile = ".env";
    private static Dotenv dotenv = Dotenv.configure().filename(developEnvFile).load();
    private static final String GOOGLE_APP_PASSWORD = dotenv.get("GOOGLE_APP_PASSWORD", "EMPTY_VALUE");
    private static final String NAVER_APP_PASSWORD = dotenv.get("NAVER_APP_PASSWORD", "EMPTY_VALUE");
    private static final String OUTLOOK_APP_PASSWORD = dotenv.get("OUTLOOK_APP_PASSWORD", "EMPTY_VALUE");

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");


//        String sender = "2326010@office.ut.ac.kr";
//        String sender = "boxsunny@outlook.com";
        String sender = "rlaalsdn110@gmail.com";
        String title = "테스트 메일입니다.";
        String content = "안녕하세요 ㅎㅇㅎㅇ";

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, GOOGLE_APP_PASSWORD);
            }
        });
        Message message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(sender));
            List<String> strings = List.of("minu421@naver.com", "sunnybikers@naver.com");
            message.setRecipients(Message.RecipientType.TO, strings.stream().map(s -> {
                try { return new InternetAddress(s); }
                catch (Exception e) { throw new RuntimeException(e); }
            }).
                    toArray(InternetAddress[]::new));
            message.setSubject(title);
            message.setContent(content, "text/html; charset=utf-8");

            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
