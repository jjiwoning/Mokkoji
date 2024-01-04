package com.ssafy.Mokkoji.core.user.infra;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ssafy.Mokkoji.core.user.domain.PasswordEncoder;

@DisplayName("PBKDF2PasswordEncoder 단위 테스트")
class PBKDF2PasswordEncoderTest {

	private PasswordEncoder passwordEncoder = new PBKDF2PasswordEncoder();

	@Test
	@DisplayName("rawPassword를 encoding 한다.")
	void encode() {
		// given
		String rawPassword = "password1@";

		// when
		String encodedPassword = passwordEncoder.encode(rawPassword);

		// then
		assertThat(encodedPassword.length()).isEqualTo(68); // hash 64 + salt 4
	}

	@Test
	@DisplayName("rawPassword와 encoding된 password가 같다면 true를 반환한다.")
	void match() {
		// given
		String rawPassword = "password1@";
		String encodedPassword = passwordEncoder.encode(rawPassword);

		// when
		boolean result = passwordEncoder.matches(rawPassword, encodedPassword);

		// then
		assertThat(result).isTrue();
	}

	@Test
	@DisplayName("rawPassword와 encoding된 password가 다르다면 true를 반환한다.")
	void matchFail() {
		// given
		String rawPassword = "password1@";
		String encodedPassword = passwordEncoder.encode(rawPassword);

		String otherPassword = "passwrd1@";

		// when
		boolean result = passwordEncoder.matches(otherPassword, encodedPassword);

		// then
		assertThat(result).isFalse();
	}
}
