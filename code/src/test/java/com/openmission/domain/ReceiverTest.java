package com.openmission.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.openmission.domain.entity.Receiver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ReceiverTest {
    @Test
    void 빈_입력값() {
        assertThatThrownBy(() -> {
            Receiver.from("");
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("빈 값입니다.")
        ;
    }

    @ParameterizedTest
    @ValueSource(strings = {"@naver.com", "asdf@.com", "asdf@", "asdf@naver", "asdfnaver.com"})
    void 누락된_값이_존재하는_이메일(String input) {
        assertThatThrownBy(() -> {
            Receiver.from(input);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 이메일입니다.")
        ;
    }

    @ParameterizedTest
    @ValueSource(strings = {"asdf@naver.com", "asdf@google.com", "a@a.om", "a2@a2.com"})
    void 유효한_이메일(String input) {
        // given
        Receiver receiver;

        // when
        receiver = Receiver.from(input);

        // then
        assertThat(receiver).isNotNull();
    }

}
