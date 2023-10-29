package com.ssafy.Mokkoji.core.attraction.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("sido 단위 테스트")
class SidoTest {

    @Test
    @DisplayName("sido 생성 테스트")
    void create() {
        // given
        int sidoCode = 1;
        String sidoName = "testSi";

        // when
        Sido sido = Sido.builder()
                .sidoCode(sidoCode)
                .sidoName(sidoName)
                .build();

        // then
        Assertions.assertAll(
                () -> assertThat(sido)
                        .extracting(Sido::getSidoCode).isEqualTo(sidoCode),
                () -> assertThat(sido)
                        .extracting(Sido::getSidoName).isEqualTo(sidoName)
        );
    }
}
