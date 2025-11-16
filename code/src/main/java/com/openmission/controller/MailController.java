package com.openmission.controller;

import com.openmission.View.InputView;
import com.openmission.View.OutputView;
import com.openmission.domain.Mail;
import com.openmission.domain.Receiver;
import com.openmission.domain.Receivers;
import com.openmission.domain.Sender;
import com.openmission.util.TriConsumer;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class MailController {

    public void run() {
        InputView.printStartMessage();
        sendMail();
    }

    private void sendMail() {
//        checkDraftMail();

        Sender sender = get(this::getSender);
        Receivers receivers = get(this::getReceivers);
        Mail mail = get(() -> Mail.of(InputView.enterTitle(), InputView.enterContent(), sender.getSession()));

        accept(Sender::send, sender, mail, receivers);
        OutputView.printSendMailResult(receivers.getMails());
    }


    private Sender getSender() {
        return Sender.from(InputView.enterSenderMail());
    }

    private Receivers getReceivers() {
        List<Receiver> receiverList = new ArrayList<>();
        while (true) {
            String mail = InputView.enterReceiverMail();
            if (mail.equals(".")) {
                break;
            }
            receiverList.add(Receiver.from(mail));
        }
        if (receiverList.isEmpty()) {
            throw new IllegalArgumentException("지정된 수신자의 이메일이 없습니다.");
        }
        return Receivers.from(receiverList);
    }

    private <T> T get(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (Exception e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    private <T, U, R> void accept(TriConsumer<T, U, R> consumer, T t, U u, R r) {
        while (true) {
            try {
                consumer.accept(t, u, r);
                return ;
            } catch (Exception e) {
                OutputView.printError(e.getMessage());
            }
        }
    }
}
