package com.openmission.controller;

import com.openmission.util.Utils;
import com.openmission.view.InputView;
import com.openmission.view.OutputView;
import com.openmission.domain.Mail;
import com.openmission.domain.Receiver;
import com.openmission.domain.Receivers;
import com.openmission.domain.Sender;
import java.util.ArrayList;
import java.util.List;

public class MailController {

    public void run() {
        InputView.printStartMessage();
        sendMail();
    }

    private void sendMail() {
//        checkDraftMail();
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
        List<Receiver> receivers = new ArrayList<>();
        while (true) {
            String mail = InputView.enterReceiverMail();
            if (mail.equals(".")) {
                break;
            }
            receivers.add(Receiver.from(mail));
        }
        if (receivers.isEmpty()) {
            throw new IllegalArgumentException("지정된 수신자의 이메일이 없습니다.");
        }
        return receivers;
    }


}
