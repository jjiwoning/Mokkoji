package com.ssafy.Mokkoji.core.user.exception;

import org.springframework.http.HttpStatus;

public class PasswordEncodingException extends UserException {

	public PasswordEncodingException(final Throwable cause) {
		super("비밀번호 인코딩 과정 중 문제가 발생했습니다.", cause, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
