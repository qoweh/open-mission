package com.openmission.View;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String START_MESSAGE = "메일 전송 프로그램을 시작합니다." + System.lineSeparator();
    private static final String SENDER_MESSAGE = "발신자의 이메일을 적절하게 입력해주세요.";
    private static final String RECEIVER_MESSAGE = "수신자의 이메일을 적절하게 입력해주세요. 입력을 그만하시려면 기호 '.'를 입력해주세요.";
    private static final String TITLE_MESSAGE = "전송하려는 메일의 제목을 입력해주세요.";
    private static final String CONTENT_MESSAGE = "전송하려는 메일의 내용을 입력해주세요.";
    private static final String DRAFT_MESSAGE = "임시 저장을 원하시면 알파벳 'D'를 입력하세요.";

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

    private static String read(String message) {
        System.out.println(message);
        return Console.readLine();
    }
}
