package com.openmission.view.;

import com.openmission.domain.DraftMail;
import java.util.List;

public class OutputView {
    private static final String MAILS_MESSAGE_FORMAT = System.lineSeparator() + "[수신자 메일 목록]";
    private static final String RESULT_MESSAGE = "메일을 전송하였습니다.";
    private static final String DRAFT_MESSAGE_FORMAT = "임시 저장된 메일들입니다.";

    public static void printError(String message) {
        System.out.println(message);
    }

    public static void printSendMailResult(List<String> mails) {
        System.out.println(MAILS_MESSAGE_FORMAT);
        mails.forEach(System.out::println);
        System.out.println(System.lineSeparator() + RESULT_MESSAGE);
    }


    public static void printDraftNotify() {
        System.out.println(DRAFT_MESSAGE_FORMAT);
    }

    public static void printDraftMailTitle(DraftMail draftMail, Integer index) {
        System.out.println(index + "번째 메일: " + draftMail.getTitle());
    }

    public static void printDraftMailDetail(DraftMail draftMail) {
        System.out.println("발신자: " + draftMail.getSender().getMail());
        System.out.println("수신자: " + draftMail.getReceivers().getMails());
        System.out.println("제목: " + draftMail.getTitle());
        System.out.println("내용: " + draftMail.getContent());
    }
}
