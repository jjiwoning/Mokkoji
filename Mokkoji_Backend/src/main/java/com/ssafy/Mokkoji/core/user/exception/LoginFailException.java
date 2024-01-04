package com.ssafy.Mokkoji.core.user.exception;

import org.springframework.http.HttpStatus;

public class LoginFailException extends UserException {

	public LoginFailException() {
		super("로그인에 실패 했습니다.", HttpStatus.BAD_REQUEST);
	}
}
