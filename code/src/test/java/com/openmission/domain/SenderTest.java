package com.openmission.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SenderTest {
    @Test
    void 빈_입력값() {
        assertThatThrownBy(() -> {
            Sender.from("");
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("빈 값입니다.")
        ;
    }

    @ParameterizedTest
    @ValueSource(strings = {"@naver.com", "asdf@.com", "asdf@naver"})
    void 누락된_값이_존재하는_이메일(String input) {
        assertThatThrownBy(() -> {
            Sender.from(input);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("지원하지 않는 도메인의 이메일입니다.")
        ;
    }

    @ParameterizedTest
    @ValueSource(strings = {"asdf@navers.com", "asdf@goooooogle.com", "asdf@naver.net", "asdf@google.net"})
    void 지원하지_않는_도메인의_이메일(String input) {
        assertThatThrownBy(() -> {
            Sender.from(input);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("지원하지 않는 도메인의 이메일입니다.")
        ;
    }

    @ParameterizedTest
    @ValueSource(strings = {"asdf@naver.com", "asdf@google.com"})
    void 지원하는_도메인의_이메일(String input) {
        // given
        Sender sender;

        // when
        sender = Sender.from(input);

        // then
        assertThat(sender).isNotNull();
    }

}
