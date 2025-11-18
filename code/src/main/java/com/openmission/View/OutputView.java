package com.openmission.view;

import java.util.List;

public class OutputView {

    private static final String MAILS_MESSAGE_FORMAT = "[수신자 메일 목록]";
    private static final String RESULT_MESSAGE = "메일을 전송하였습니다.";

    public static void printError(String message) {
        System.out.println(message);
    }

    public static void printSendMailResult(List<String> mails) {
        System.out.println(MAILS_MESSAGE_FORMAT);
        mails.forEach(System.out::println);
        System.out.println(System.lineSeparator() + RESULT_MESSAGE);
    }

}
