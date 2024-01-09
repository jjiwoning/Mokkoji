package com.ssafy.Mokkoji.core.user.exception;

import org.springframework.http.HttpStatus;

public class JwtInvalidException extends UserException {

	public JwtInvalidException() {
		super("토큰이 유효하지 않습니다.", HttpStatus.BAD_REQUEST);
	}
}
