package com.ssafy.Mokkoji.core.user.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ssafy.Mokkoji.core.user.domain.vo.NickName;
import com.ssafy.Mokkoji.core.user.domain.vo.Password;
import com.ssafy.Mokkoji.core.user.exception.LoginFailException;

import helper.domain.UserDomainHelper;
import helper.mock.MockPasswordEncoder;

@DisplayName("User 단위 테스트")
class UserTest {

	private final PasswordEncoder passwordEncoder = new MockPasswordEncoder();

	@Test
	@DisplayName("로그인을 할 수 있다.")
	void login() {
		// given
		User user = UserDomainHelper.buildDefaultUser()
			.encodedPassword(MockPasswordEncoder.ENCODED_PASSWORD)
			.build();
		String rawPassword = "rawPassword";

		// when, then
		assertThatCode(() -> user.login(rawPassword, passwordEncoder))
			.doesNotThrowAnyException();
	}

	@Test
	@DisplayName("비밀번호가 틀리면 로그인에 실패한다.")
	void loginFail() {
		User user = UserDomainHelper.buildDefaultUser()
			.encodedPassword("ahsdasdkdas")
			.build();

		assertThatThrownBy(() -> user.login(user.getPassword().getValue(), passwordEncoder))
			.isInstanceOf(LoginFailException.class);
	}

	@Test
	@DisplayName("회원 정보를 수정할 수 있다.")
	void updateUser() {
		User user = UserDomainHelper.buildDefaultUser().build();

		user.updateUser("hello123@mail.com", "hello123", passwordEncoder.encode("hello!!!"), "01010101");

		assertThat(user).extracting(User::getNickname)
			.isEqualTo(NickName.from("hello123"));
		assertThat(user).extracting(User::getPassword)
			.isEqualTo(Password.from(MockPasswordEncoder.ENCODED_PASSWORD));
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
