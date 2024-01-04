package com.ssafy.Mokkoji.core.user.domain.vo;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ssafy.Mokkoji.core.user.exception.LoginIdInvalidException;

@DisplayName("LoginId 단위 테스트")
class LoginIdTest {

	@Test
	@DisplayName("올바른 입력이 들어오면 LoginId 생성에 성공한다.")
	void test1() {
		// given
		String value = "hello123";

		// when, then
		assertThatCode(() -> LoginId.from(value))
			.doesNotThrowAnyException();
	}

	@Test
	@DisplayName("공백이 포함된 입력이 들어오면 LoginId 생성에 실패한다.")
	void test2() {
		// given
		String value = " h el o2";

		// when, then
		assertThatThrownBy(() -> LoginId.from(value))
			.isInstanceOf(LoginIdInvalidException.class)
			.hasMessage("아이디는 5 ~ 12 글자 사이로 공백 없이 입력해주세요");
	}

	@Test
	@DisplayName("5글자 미만의 입력이 들어오면 LoginId 생성에 실패한다.")
	void test3() {
		// given
		String value = "jasd";

		// when, then
		assertThatThrownBy(() -> LoginId.from(value))
			.isInstanceOf(LoginIdInvalidException.class)
			.hasMessage("아이디는 5 ~ 12 글자 사이로 공백 없이 입력해주세요");
	}

	@Test
	@DisplayName("12글자 초과의 입력이 들어오면 LoginId 생성에 실패한다.")
	void test4() {
		// given
		String value = "abcdefjsqwe214r5r2";

		// when, then
		assertThatThrownBy(() -> LoginId.from(value))
			.isInstanceOf(LoginIdInvalidException.class)
			.hasMessage("아이디는 5 ~ 12 글자 사이로 공백 없이 입력해주세요");
	}
}
