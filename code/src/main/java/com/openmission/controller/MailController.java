package com.openmission.controller;

import com.openmission.domain.Mail;
import com.openmission.domain.Receiver;
import com.openmission.domain.Receivers;
import com.openmission.domain.Sender;
import com.openmission.util.Utils;
import com.openmission.view.InputView;
import com.openmission.view.OutputView;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class MailController {
    public void run() {
        InputView.printStartMessage();
        Utils.retryUntilGet("n", () -> process());
    }

    private String process() {
        sendMail();
        return InputView.enterRetry();
    }

    private void sendMail() {
        Sender sender = Utils.get(() -> getSender());
        Receivers receivers = Utils.get(() -> getReceivers());
        Mail mail = Utils.get(() -> getMail(sender));

        if (!Utils.accept(Sender::send, sender, mail, receivers)) return ;
        OutputView.printSendMailResult(receivers.getReceiversMails());
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
}
