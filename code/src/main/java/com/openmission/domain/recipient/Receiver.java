package com.openmission.domain.recipient;

public class Receiver {
    private final String mail;
    private static final String EMPTY_STRING = "빈 값입니다.";
    private static final String INVALID_DOMAIN_MAIL = "유효하지 않은 이메일입니다.";
    private static final String MAIL_PATTERN = "^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$";

    private Receiver(String mail) {
        this.mail = mail;
    }

    public static Receiver from(String mail) {
        validate(mail);
        return new Receiver(mail);
    }

    private static void validate(String mail) {
        if (mail.isBlank()) {
            throw new IllegalArgumentException(EMPTY_STRING);
        }
        if (!mail.matches(MAIL_PATTERN)) {
            throw new IllegalArgumentException(INVALID_DOMAIN_MAIL);
        }
    }

    public String getMail() {
        return this.mail;
    }
}
