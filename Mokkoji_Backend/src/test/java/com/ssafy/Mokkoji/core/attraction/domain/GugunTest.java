package com.ssafy.Mokkoji.core.attraction.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("gugun 단위 테스트")
class GugunTest {

    @Test
    @DisplayName("gugun 생성 테스트")
    void create() {
        // given
        int gugunCode = 1;
        String gugunName = "testGugun";
        Sido sido = Sido.builder()
                .sidoCode(1)
                .sidoName("testSi")
                .build();

        // when
        Gugun gugun = Gugun.builder()
                .gugunCode(gugunCode)
                .gugunName(gugunName)
                .sido(sido)
                .build();

        // then
        Assertions.assertAll(
                () -> assertThat(gugun)
                        .extracting(Gugun::getGugunCode).isEqualTo(gugunCode),
                () -> assertThat(gugun)
                        .extracting(Gugun::getGugunName).isEqualTo(gugunName),
                () -> assertThat(gugun)
                        .extracting(Gugun::getSido).isEqualTo(sido)
        );
    }
}
