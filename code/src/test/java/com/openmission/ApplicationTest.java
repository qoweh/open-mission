package com.openmission;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.List;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    @Test
    void 기능_테스트() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("sunnybikers@naver.com",
                            "minu421@naver.com",
                            "sunnybikers@naver.com",
                            ".",
                            "제목",
                            "내용",
                            "@",
                            "n"
                    );
                    assertThat(output()).contains(
                            "minu421@naver.com",
                            "sunnybikers@naver.com",
                            "메일을 전송하였습니다."
                    );
                },
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44),
                List.of(1, 8, 11, 31, 41, 42),
                List.of(13, 14, 16, 38, 42, 45),
                List.of(7, 11, 30, 40, 42, 43),
                List.of(2, 13, 22, 32, 38, 45),
                List.of(1, 3, 5, 14, 22, 45)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
