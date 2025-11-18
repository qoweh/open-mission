package com.openmission.controller;

import com.openmission.util.Utils;
import com.openmission.view.InputView;
import com.openmission.view.OutputView;
import com.openmission.domain.entity.Mail;
import com.openmission.domain.entity.Receiver;
import com.openmission.domain.entity.Receivers;
import com.openmission.domain.entity.Sender;
import java.util.ArrayList;
import java.util.List;

public class MailController {
    public void run() {
        InputView.printStartMessage();
        Utils.retryUntilGet("n", () -> process());
    }

    private String process() {
        checkDraftMail();
        sendMail();
        return InputView.enterRetry();
    }

    private void sendMail() {
        Sender sender = Utils.get(() -> getSender());
        Receivers receivers = Utils.get(() -> getReceivers());
        Mail mail = Utils.get(() -> Mail.of(InputView.enterTitle(), InputView.enterContent(), sender.getSession()));

        Utils.accept(Sender::send, sender, mail, receivers);
        OutputView.printSendMailResult(receivers.getMails());
    }

    private Sender getSender() {
        return Sender.from(InputView.enterSenderMail());
    }

    private Receivers getReceivers() {
        List<Receiver> receivers = createReceivers();
        return Receivers.from(receivers);
    }

    private static List<Receiver> createReceivers() {
        List<Receiver> receivers = enterReceivers(new ArrayList<>());
        if (receivers.isEmpty()) {
            throw new IllegalArgumentException("지정된 수신자의 이메일이 없습니다.");
        }
        return receivers;
    }

    private static List<Receiver> enterReceivers(List<Receiver> receivers) {
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
            receivers.add(createReceiver(mail));
        }
        return receivers;
    }

    private static Receiver createReceiver(String mail) {
        return Receiver.from(mail);
    }
}
