package com.ssafy.Mokkoji.core.user.domain.vo;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ssafy.Mokkoji.core.user.exception.NameInvalidException;

@DisplayName("Name 단위 테스트")
class NameTest {

	@Test
	@DisplayName("올바른 입력이 들어오면 닉네임 생성에 성공한다.")
	void test1() {
		// given
		String value = "최탐탐";

		// when, then
		assertThatCode(() -> Name.from(value))
			.doesNotThrowAnyException();
	}

	@Test
	@DisplayName("공백이 포함된 입력이 들어오면 Name 생성에 실패한다.")
	void test2() {
		// given
		String value = "최 탐탐";

		// when, then
		assertThatThrownBy(() -> Name.from(value))
			.isInstanceOf(NameInvalidException.class);
	}

	@Test
	@DisplayName("2글자 미만의 입력이 들어오면 Name 생성에 실패한다.")
	void test3() {
		// given
		String value = "최";

		// when, then
		assertThatThrownBy(() -> Name.from(value))
			.isInstanceOf(NameInvalidException.class);
	}
}
