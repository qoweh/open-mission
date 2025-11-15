package com.openmission.domain;

import jakarta.mail.Address;

public class Receiver {
    private static final String EMPTY_STRING = "빈 값입니다.";
    private static final String INVALID_DOMAIN_MAIL = "유효하지 않은 이메일입니다.";
    private static final String MAIL_PATTERN = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";

    private Receiver(String mail) {
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

    public Address[] getReceivers() {
        return null;
    }
}
