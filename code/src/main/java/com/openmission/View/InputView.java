package com.openmission.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String RECEIVER_SPECIAL_CHARACTER = ".";
    private static final String DRAFT_SPECIAL_CHARACTER = "STOP";
    private static final String START_MESSAGE = "메일 전송 프로그램을 시작합니다." + System.lineSeparator();
    private static final String SENDER_MESSAGE = "발신자의 이메일을 적절하게 입력해주세요.";
    private static final String RECEIVER_MESSAGE = "수신자의 이메일을 적절하게 입력해주세요. 입력을 그만하시려면 기호 '" + RECEIVER_SPECIAL_CHARACTER + "'를 입력해주세요.";
    private static final String TITLE_MESSAGE = "전송하려는 메일의 제목을 입력해주세요.";
    private static final String CONTENT_MESSAGE = "전송하려는 메일의 내용을 입력해주세요.";
    private static final String RETRY_MESSAGE = "이어서 메일을 계속 전송하시려면 y, 아니라면 n를 입력해주세요.";

    public static void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public static String enterSenderMail() {
        return read(SENDER_MESSAGE);
    }

    public static String enterReceiverMail() {
        return read(RECEIVER_MESSAGE);
    }

    public static String enterTitle() {
        return read(TITLE_MESSAGE);
    }

    public static String enterContent() {
        return read(CONTENT_MESSAGE);
    }

    public static String enterRetry() {
        return read(RETRY_MESSAGE);
    }

    private static String read(String message) {
        System.out.println(message);
        return Console.readLine();
    }
}
