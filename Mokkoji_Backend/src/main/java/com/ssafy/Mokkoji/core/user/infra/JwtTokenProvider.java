package com.ssafy.Mokkoji.core.user.infra;

import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.ssafy.Mokkoji.core.user.domain.TokenProvider;
import com.ssafy.Mokkoji.core.user.exception.JwtInvalidException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider implements TokenProvider {

	// 원시적인 문자열을 키 인수로 사용하지 않고 더 안전하게 사용하는 방법
	private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	private static final long ACCESS_TOKEN_EXPIRE_SECOND = TimeUnit.HOURS.toMillis(1);

	@Override
	public String createAccessToken(final Long userId) {
		Claims claims = Jwts.claims()
			.setSubject(String.valueOf(userId))
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_SECOND));

		return Jwts.builder()
			.signWith(this.secretKey)
			.setClaims(claims)
			.compact();
	}

	@Override
	public boolean validateToken(final String token) {
		try {
			Claims body = Jwts.parserBuilder()
				.setSigningKey(this.secretKey)
				.build()
				.parseClaimsJws(token)
				.getBody();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Long parseToken(final String token) {
		String subject = Jwts.parserBuilder()
			.setSigningKey(this.secretKey)
			.build()
			.parseClaimsJws(token)
			.getBody()
			.getSubject();

		return parseLong(subject);
	}

	private Long parseLong(final String subject) {
		try {
			return Long.parseLong(subject);
		} catch (NumberFormatException e) {
			throw new JwtInvalidException();
		}
	}
}
