package com.ssafy.Mokkoji.core.board.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Content 단위 테스트")
class ContentTest {

    @Test
    @DisplayName("Content 생성에 성공한다.")
    void create() {
        // given
        String value = "hello";

        // when, then
        Assertions.assertThatCode(() -> Content.from(value))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("빈 값이 들어오면 Content 생성시 예외가 발생한다.")
    void createException1() {
        // given
        String value = "";

        // when, then
        Assertions.assertThatThrownBy(() -> Content.from(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("null 값이 들어오면 Content 생성시 예외가 발생한다.")
    void createException2() {
        // given
        String value = null;

        // when, then
        Assertions.assertThatThrownBy(() -> Content.from(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("공백 값이 들어오면 Content 생성시 예외가 발생한다.")
    void createException3() {
        // given
        String value = "   ";

        // when, then
        Assertions.assertThatThrownBy(() -> Content.from(value))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
