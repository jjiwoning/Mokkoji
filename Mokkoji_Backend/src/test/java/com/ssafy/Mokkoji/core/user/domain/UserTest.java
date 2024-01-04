package com.ssafy.Mokkoji.core.user.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ssafy.Mokkoji.core.user.domain.vo.NickName;

import helper.domain.UserDomainHelper;

@DisplayName("User 단위 테스트")
class UserTest {

	@Test
	@DisplayName("로그인을 할 수 있다.")
	void login() {
		User user = UserDomainHelper.buildDefaultUser().build();

		assertThat(user.login(user.getPassword())).isTrue();
	}

	@Test
	@DisplayName("비밀번호가 틀리면 로그인에 실패한다.")
	void loginFail() {
		User user = UserDomainHelper.buildDefaultUser().build();

		assertThat(user.login("hello123")).isFalse();
	}

	@Test
	@DisplayName("회원 정보를 수정할 수 있다.")
	void updateUser() {
		User user = UserDomainHelper.buildDefaultUser().build();

		user.updateUser("hello123@mail.com", "hello123", "hello123", "01010101");

		assertThat(user).extracting(User::getNickname).isEqualTo(NickName.from("hello123"));
		assertThat(user).extracting(User::getPassword).isEqualTo("hello123");
	}

	@Test
	@DisplayName("같은 유저이면 true를 반환한다.")
	void isSameUser() {
		User user = UserDomainHelper.buildDefaultUser().userId(1L).build();

		assertThat(user.isSameUser(1L)).isTrue();
	}

	@Test
	@DisplayName("다른 유저이면 false를 반환한다.")
	void isNotSameUser() {
		User user = UserDomainHelper.buildDefaultUser().userId(1L).build();

		assertThat(user.isSameUser(3L)).isFalse();
	}
}
