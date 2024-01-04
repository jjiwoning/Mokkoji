package com.ssafy.Mokkoji.core.user.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import com.ssafy.Mokkoji.core.user.exception.PasswordInvalidException;

@DisplayName("PasswordValidator 단위 테스트")
class PasswordValidatorTest {

	private final PasswordValidator passwordValidator = new PasswordValidator();

	@Test
	@DisplayName("8~16자 영문 대 소문자, 숫자, 특수문자를 사용하는 비밀번호면 예외가 발생하지 않는다.")
	void test1() {
		// given
		String rawPassword = "Aab1234!@#$";

		// when, then
		assertThatCode(() -> passwordValidator.validatePassword(rawPassword))
			.doesNotThrowAnyException();
	}

	@ParameterizedTest
	@ValueSource(strings = {"123", "abc23455", "Aaaannn", "Aa2!"})
	@NullAndEmptySource
	@DisplayName("잘못된 비밀번호 형식이 들어오면 예외가 발생한다.")
	void test2(String rawPassword) {
		// when, then
		assertThatThrownBy(() -> passwordValidator.validatePassword(rawPassword))
			.isInstanceOf(PasswordInvalidException.class);
	}
}
