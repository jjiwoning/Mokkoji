package com.ssafy.Mokkoji.core.user.domain.vo;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import com.ssafy.Mokkoji.core.user.exception.MailInvalidException;

@DisplayName("Mail 단위 테스트")
class MailTest {

	@Test
	@DisplayName("올바른 이메일 형식이 들어오면 Mail 생성에 성공한다.")
	void test1() {
		// given
		String value = "tamtam@hello.com";

		// when, then
		assertThatCode(() -> Mail.from(value))
			.doesNotThrowAnyException();
	}

	@ParameterizedTest
	@ValueSource(strings = {"tamtam.com", "tam@hello", "tam@", "asdrw"})
	@NullAndEmptySource
	@DisplayName("잘못된 이메일 형식이 들어오면 Mail 생성에 실패한다.")
	void test2(final String value) {
		// when, then
		assertThatThrownBy(() -> Mail.from(value))
			.isInstanceOf(MailInvalidException.class)
			.hasMessage("올바른 이메일 형식으로 입력해주세요.");
	}
}
