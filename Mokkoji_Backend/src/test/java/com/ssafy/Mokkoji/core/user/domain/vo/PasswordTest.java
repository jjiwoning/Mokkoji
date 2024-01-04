package com.ssafy.Mokkoji.core.user.domain.vo;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ssafy.Mokkoji.core.user.domain.PasswordEncoder;

import helper.mock.MockPasswordEncoder;

@DisplayName("Password 단위 테스트")
class PasswordTest {

	private final PasswordEncoder passwordEncoder = new MockPasswordEncoder();

	@Test
	@DisplayName("rawPassword의 인코딩 결과와 인코딩된 패스워드가 같다면 true를 반환한다.")
	void test1() {
		// given
		Password password = Password.from(MockPasswordEncoder.ENCODED_PASSWORD);
		String rawPassword = "asdasd";

		// when
		boolean result = password.matches(rawPassword, passwordEncoder);

		// then
		assertThat(result).isTrue();
	}

	@Test
	@DisplayName("rawPassword의 인코딩 결과와 인코딩된 패스워드가 다르다면 false를 반환한다.")
	void test2() {
		// given
		Password password = Password.from("다른패스워드지롱");
		String rawPassword = "asdasd";

		// when
		boolean result = password.matches(rawPassword, passwordEncoder);

		// then
		assertThat(result).isFalse();
	}
}
