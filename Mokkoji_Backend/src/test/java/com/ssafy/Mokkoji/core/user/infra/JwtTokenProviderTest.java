package com.ssafy.Mokkoji.core.user.infra;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ssafy.Mokkoji.core.user.domain.TokenProvider;

@DisplayName("JwtTokenProvider 단위 테스트")
class JwtTokenProviderTest {

	private final TokenProvider tokenProvider = new JwtTokenProvider();

	@Test
	@DisplayName("memberId가 주어지면 토큰 생성에 성공한다.")
	void test1() {
		// given
		Long userId = 1L;

		// when
		String accessToken = tokenProvider.createAccessToken(userId);

		// then
		assertThat(accessToken.split("\\."))
			.hasSize(3);
	}

	@Test
	@DisplayName("올바른 토큰이 주어지면 이를 파싱해서 memberId를 반환한다.")
	void test2() {
		// given
		Long userId = 1L;

		String accessToken = tokenProvider.createAccessToken(userId);

		// when
		Long result = tokenProvider.parseToken(accessToken);

		// then
		assertThat(userId).isEqualTo(result);
	}

	@Test
	@DisplayName("유효기간이 지난 토큰이 주어지면 false를 반환한다.")
	void test3() {
		// given
		String expiredToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNzA0Nzg2NDg3LCJleHAiOjE3MDQ3ODY0ODd9.xBQae3Vl1FYrIycFhUxPCp7oJeRYu6_H7w92qxQNhr8";

		// when
		boolean result = tokenProvider.validateToken(expiredToken);

		// then
		assertThat(result).isFalse();
	}

	@Test
	@DisplayName("잘못된 토큰이 주어지면 false를 반환한다.")
	void test4() {
		// given
		String invalidToken = "asd.asd.fed";

		// when
		boolean result = tokenProvider.validateToken(invalidToken);

		// then
		assertThat(result).isFalse();
	}
}
