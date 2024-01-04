package com.ssafy.Mokkoji.core.user.domain.vo;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import com.ssafy.Mokkoji.core.user.exception.PhoneNumberInvalidException;

@DisplayName("PhoneNumber 단위 테스트")
class PhoneNumberTest {

	@Test
	@DisplayName("올바른 전화번호 형식이 들어오면 PhoneNumber 생성에 성공한다.")
	void test1() {
		// given
		String phoneNumber = "01000000000";

		// when, then
		assertThatCode(() -> PhoneNumber.from(phoneNumber))
			.doesNotThrowAnyException();
	}

	@ParameterizedTest
	@ValueSource(strings = {"123", "010aa222222", "12900000000", "0100000O000"})
	@NullAndEmptySource
	@DisplayName("잘못된 전화번호 형식이 들어오면 예외가 발생한다.")
	void test2(String phoneNumber) {
		// when, then
		assertThatThrownBy(() -> PhoneNumber.from(phoneNumber))
			.isInstanceOf(PhoneNumberInvalidException.class);
	}
}
