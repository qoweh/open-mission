package com.openmission.controller;

import com.openmission.View.InputView;
import com.openmission.View.OutputView;
import com.openmission.domain.DraftMail;
import com.openmission.domain.DraftMails;
import com.openmission.domain.Mail;
import com.openmission.domain.Receiver;
import com.openmission.domain.Receivers;
import com.openmission.domain.Sender;
import com.openmission.util.Utils;
import java.util.ArrayList;
import java.util.List;

public class MailController {

    public void run() {
        InputView.printStartMessage();
        DraftMails draftMails = new DraftMails();
        while (true) {
            draftMails = Utils.runUntilValid(this::sendMail, draftMails);
        }
    }

    private DraftMails sendMail(DraftMails draftMails) {
        DraftMail savedDraftMail = checkDraftMail(draftMails);
        DraftMail draftMail = (savedDraftMail == null) ? new DraftMail() : savedDraftMail;

        try {
            Sender sender = Utils.getUntilValid(() -> enterSender(draftMail));
            Receivers receivers = Utils.getUntilValid(() -> enterReceivers(draftMail));
            String title = Utils.getUntilValid(() -> enterTitle(draftMail));
            String content = Utils.getUntilValid(() -> enterContent(draftMail));
            Mail mail = getMail(title, content, sender, draftMail);
            lastCheck();

            Utils.runUntilValid(Sender::send, sender, receivers, mail);
            OutputView.printSendMailResult(receivers.getMails());
        } catch (RuntimeException e) {
            draftMails.add(draftMail);
        }
        return draftMails;
    }

    private DraftMail checkDraftMail(DraftMails draftMails) {
        if (draftMails.isEmpty())
            return null;
        OutputView.printDraftNotify();
        Utils.eachMails(draftMails, OutputView::printDraftMailTitle);
        // 1~n으로 골라야 하고 0 입력 시 고르지 않음.
        Integer pickDraftMail = Integer.valueOf(String.valueOf(InputView.enterPickDraftMail())) - 1;
        if (pickDraftMail == -1) {
            return null;
        }
        DraftMail draftMail = draftMails.getMail(pickDraftMail);
        OutputView.printDraftMailDetail(draftMail);
        return draftMail;
    }

    private Sender enterSender(DraftMail draftMail) {
        Sender sender = Sender.from(InputView.enterSenderMail());
        draftMail.setSender(sender);
        return sender;
    }

    private Receivers enterReceivers(DraftMail draftMail) {
        if (draftMail.getReceivers() != null) return draftMail.getReceivers();
        List<Receiver> receiverList = new ArrayList<>();
        while (true) {
            try {
                String mail = Utils.wantDraft(InputView.enterReceiverMail());
                if (mail.equals(".")) {
                    break;
                }
                receiverList.add(Receiver.from(mail));
            } catch (Exception e) {
                break;
            }
        }
        if (receiverList.isEmpty()) {
            throw new IllegalArgumentException("지정된 수신자의 이메일이 없습니다.");
        }
        Receivers receivers = Receivers.from(receiverList);
        draftMail.setReceivers(receivers);
        return receivers;
    }

    private String enterTitle(DraftMail draftMail) {
        if (draftMail.getTitle() != null) return draftMail.getTitle();
        String title = Utils.wantDraft(InputView.enterTitle());
        draftMail.setTitle(title);
        return title;
    }

    private String enterContent(DraftMail draftMail) {
        if (draftMail.getContent() != null) return draftMail.getContent();
        String content = Utils.wantDraft(InputView.enterContent());
        draftMail.setContent(content);
        return content;
    }

    private Mail getMail(String title, String content, Sender sender, DraftMail draftMail) {
        Mail mail = Mail.of(title, content, sender.getSession());
        draftMail.setMail(mail);
        return mail;
    }

    private void lastCheck() {
        Utils.wantDraft(InputView.enterLastCheck());
    }
}
