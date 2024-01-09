package com.ssafy.Mokkoji.core.user.dto.response;

import com.ssafy.Mokkoji.core.user.domain.RefreshToken;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class RefreshTokenResponse {
	private String refreshToken;

	public RefreshTokenResponse(final RefreshToken refreshToken) {
		this.refreshToken = refreshToken.getRefreshToken();
	}
}
