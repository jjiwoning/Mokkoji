package com.ssafy.Mokkoji.core.user.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.Builder;
import lombok.Getter;

@RedisHash(value = "refreshToken", timeToLive = 1209600)
@Getter
public class RefreshToken {

	@Id
	@Indexed
	private String refreshToken;

	private Long userId;

	@Builder
	public RefreshToken(final String refreshToken, final Long userId) {
		this.refreshToken = refreshToken;
		this.userId = userId;
	}
}
