package com.openmission.view;

import java.time.LocalDateTime;
import java.util.List;

public class OutputView {
    private static final String MAILS_MESSAGE_FORMAT = System.lineSeparator() + "[수신자 메일 목록]";
    private static final String RESULT_MESSAGE = "메일을 전송하였습니다.";

    public static void printError(String message) {
        System.out.println(message);
    }

    public static void printSendMailResult(List<String> mails) {
        System.out.println(MAILS_MESSAGE_FORMAT);
        mails.forEach(System.out::println);
        System.out.println(System.lineSeparator() + RESULT_MESSAGE);
    }

    public static void printLogs(String sender, List<String> receivers, String title, LocalDateTime localDateTime) {
        System.out.println("발신자:" + sender);
        System.out.println("수신자:" + receivers);
        System.out.println("제목:" + title);
        System.out.println("일시:" + localDateTime);
        System.out.println();
    }
}
