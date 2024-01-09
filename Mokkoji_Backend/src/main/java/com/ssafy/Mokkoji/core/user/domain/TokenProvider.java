package com.ssafy.Mokkoji.core.user.domain;

public interface TokenProvider {

	String createAccessToken(Long userId);

	boolean validateToken(String token);

	Long parseToken(String token);
}
