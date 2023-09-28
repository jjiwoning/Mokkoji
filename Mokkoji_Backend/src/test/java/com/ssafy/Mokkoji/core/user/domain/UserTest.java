package com.ssafy.Mokkoji.core.user.domain;

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

        user.updateUser("hello123@mail.com", "hello123", "hello123", "01010101");

        assertThat(user).extracting(User::getNickname).isEqualTo("hello123");
        assertThat(user).extracting(User::getPassword).isEqualTo("hello123");
    }

    @Test
    @DisplayName("같은 유저이면 true를 반환한다.")
    void isSameUser() {
        User user = User.builder().userId(1L).build();

        assertThat(user.isSameUser(1L)).isTrue();
    }

    @Test
    @DisplayName("다른 유저이면 false를 반환한다.")
    void isNotSameUser() {
        User user = User.builder().userId(1L).build();

        assertThat(user.isSameUser(3L)).isFalse();
    }
}
