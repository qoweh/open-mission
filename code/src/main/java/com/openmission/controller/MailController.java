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

        Utils.accept(Sender::send, sender, mail, receivers);
        OutputView.printSendMailResult(receivers.getMails());
    }

    private Sender getSender() {
        Sender sender = Sender.from(InputView.enterSenderMail());
        return sender;
    }

    private Receivers getReceivers() {
        List<Receiver> receivers = createReceivers();
        List<Receiver> ccReceivers = createCCReceivers();
        return Receivers.of(receivers, ccReceivers);
    }

    private static List<Receiver> createReceivers() {
        List<Receiver> receivers = enterReceivers(new ArrayList<>());
        if (receivers.isEmpty()) {
            throw new IllegalArgumentException("지정된 수신자의 이메일이 없습니다.");
        }
        return receivers;
    }

    private List<Receiver> createCCReceivers() {
        return null;
    }

    private static List<Receiver> enterReceivers(List<Receiver> receivers) {
        while (true) {
            try {
                Receiver receiver = createReceiver(InputView.enterReceiverMail());
                receivers.add(receiver);
            } catch (Exception e) {
                break;
            }
        }
        return receivers;
    }

    private static Receiver createReceiver(String mail) {
        return Receiver.from(mail);
    }

    private Mail getMail(Sender sender) throws MessagingException {
        String title = InputView.enterTitle();
        String content = InputView.enterContent();
        Session session = sender.getSession();
        String priority = InputView.enterPriority();
        Mail mail = Mail.of(title,  content, session, priority);
        return mail;

    }
}
