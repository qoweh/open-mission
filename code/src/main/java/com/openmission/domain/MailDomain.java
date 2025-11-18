package com.openmission.domain;

import com.openmission.domain.entity.Sender;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;

public class MailDomain {
    private static final String EMPTY_STRING = "빈 값입니다.";
    private static final String INVALID_DOMAIN_MAIL = "지원하지 않는 도메인의 이메일입니다.";
    private static final String MAIL_PATTERN = "^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$";

    private static final String DOMAIN_NAVER = "@naver.com";
    private static final String DOMAIN_GOOGLE = "@google.com";

    private static final String productEnvFile = ".env.examples";
    private static final String developEnvFile = ".env";
    private static final Dotenv dotenv = Dotenv.configure().filename(developEnvFile).load();
    private static final String NAVER_APP_PASSWORD = dotenv.get("NAVER_APP_PASSWORD", "EMPTY_VALUE");
    private static final String GOOGLE_APP_PASSWORD = dotenv.get("GOOGLE_APP_PASSWORD", "EMPTY_VALUE");

    public static Sender createFrom(String mail) {
        validate(mail);

        if (mail.contains(DOMAIN_NAVER)) {
            return new NaverSender(mail, makeAuthenticator(mail, NAVER_APP_PASSWORD));
        }
        if (mail.contains(DOMAIN_GOOGLE)) {
            return new GoogleSender(mail, makeAuthenticator(mail, GOOGLE_APP_PASSWORD));
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
}
