package com.ssafy.Mokkoji.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("User 단위 테스트")
class UserTest {

    @Test
    @DisplayName("로그인을 할 수 있다.")
    void login() {
        User user = User.builder().loginId("hello").password("hello").build();

        assertThat(user.login("hello")).isTrue();
    }

    @Test
    @DisplayName("비밀번호가 틀리면 로그인에 실패한다.")
    void loginFail() {
        User user = User.builder().loginId("hello").password("hello").build();

        assertThat(user.login("hello123")).isFalse();
    }

    @Test
    @DisplayName("회원 정보를 수정할 수 있다.")
    void updateUser() {
        User user = User.builder().loginId("hello").password("hello").build();

        user.updateUser(User.builder().nickname("hello123").password("hello123").build());

        assertThat(user).extracting(User::getNickname).isEqualTo("hello123");
        assertThat(user).extracting(User::getPassword).isEqualTo("hello123");
    }
}