package com.ssafy.Mokkoji.core.user.exception;

import org.springframework.http.HttpStatus;

public class PasswordInvalidException extends UserException {

	public PasswordInvalidException() {
		super("비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.", HttpStatus.BAD_REQUEST);
	}
}
