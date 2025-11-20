package com.openmission.domain;

import com.openmission.domain.recipient.Receiver;
import com.openmission.domain.recipient.Receivers;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ReceiversTest {
    @Test
    // fix : 적절한 테스트인지 잘 모르겠음
    void 수신자와_참조자_설정() {
        // given
        String receiverMail = "test1@test.com";
        String ccReceiverMail = "test2@test.com";

        List<Receiver> receiverMails = List.of(Receiver.from(receiverMail));
        List<Receiver> ccReceiverMails = List.of(Receiver.from(ccReceiverMail));;
        Receivers receivers = Receivers.of(receiverMails, ccReceiverMails);

        // when
        List<String> receiversMails = receivers.getReceiversMails();
        List<String> ccReceiversMails = receivers.getCcReceiversMails();

        // then
        Assertions.assertThat(receiversMails).containsExactly(receiverMail);
        Assertions.assertThat(ccReceiversMails).containsExactly(ccReceiverMail);
    }
}
