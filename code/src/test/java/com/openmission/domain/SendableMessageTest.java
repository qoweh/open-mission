package com.openmission.domain;


import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SendableMessageTest {
    @Test
    void 우선순위_설정() throws MessagingException {
        // given
        Message message = new MimeMessage((Session) null);
        Priority priority = Priority.NORMAL;
        SendableMessage sendableMessage = new SendableMessage("제목", "내용", message, priority);

        // then
        Assertions.assertThat(sendableMessage.getMessage().getHeader("X-Priority")).contains(priority.name());
    }
}
