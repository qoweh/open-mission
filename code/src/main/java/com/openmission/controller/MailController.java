package com.openmission.controller;

import com.openmission.domain.mail.Mail;
import com.openmission.domain.recipient.Receiver;
import com.openmission.domain.recipient.Receivers;
import com.openmission.domain.SaveLog;
import com.openmission.domain.sender.Sender;
import com.openmission.util.Utils;
import com.openmission.view.InputView;
import com.openmission.view.OutputView;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class MailController {
    public void run() {
        List<SaveLog> logs = new ArrayList<>();
        InputView.printStartMessage();
        Utils.retryUntilGet("n", () -> process(logs));

        logs.forEach(log -> OutputView.printLogs(log.sender(), log.receivers(), log.title(), log.localDateTime()));
    }

    private String process(List<SaveLog> logs) {
        logs.add(sendMail());
        return InputView.enterRetry();
    }

    private SaveLog sendMail() {
        Sender sender = Utils.get(() -> getSender());
        Receivers receivers = Utils.get(() -> getReceivers());
        Mail mail = Utils.get(() -> getMail(sender));

        if (hasSendError(sender, mail, receivers)) return null;
        OutputView.printSendMailResult(receivers.getReceiversMails());
        return getSaveLog(sender, receivers, mail);
    }

    private Sender getSender() {
        return Sender.from(InputView.enterSenderMail());
    }

    private Receivers getReceivers() {
        List<Receiver> receivers = createReceivers(InputView::enterReceiverMail, false);
        List<Receiver> ccReceivers = createReceivers(InputView::enterCcReceiverMail, true);
        return Receivers.of(receivers, ccReceivers);
    }

    private List<Receiver> createReceivers(Supplier<String> inputView, boolean possibleEmpty) {
        return Utils.enterReceivers(new ArrayList<>(), inputView, possibleEmpty);
    }

    private Mail getMail(Sender sender) throws MessagingException {
        String title = InputView.enterTitle();
        String content = InputView.enterContent();
        Session session = sender.getSession();
        String priority = InputView.enterPriority();
        return Mail.of(title,  content, session, priority);

    }

    private boolean hasSendError(Sender sender, Mail mail, Receivers receivers) {
        int i = 3;
        while (!Utils.accept(Sender::send, sender, mail, receivers) && i > 0) {
            i--;
        }
        if (i == 0) {
            return true;
        }
        return false;
    }

    private SaveLog getSaveLog(Sender sender, Receivers receivers, Mail mail) {
        return new SaveLog(sender.getMail(), receivers.getReceiversMails(), mail.getTitle(), LocalDateTime.now());
    }
}
