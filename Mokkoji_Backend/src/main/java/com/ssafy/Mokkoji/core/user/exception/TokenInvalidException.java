package com.ssafy.Mokkoji.core.user.exception;

import org.springframework.http.HttpStatus;

public class TokenInvalidException extends UserException {

	public TokenInvalidException() {
		super("토큰이 올바르지 않습니다.", HttpStatus.BAD_REQUEST);
	}
}
