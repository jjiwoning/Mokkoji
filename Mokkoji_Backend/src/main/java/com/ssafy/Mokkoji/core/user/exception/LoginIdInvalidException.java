package com.ssafy.Mokkoji.core.user.exception;

import org.springframework.http.HttpStatus;

public class LoginIdInvalidException extends UserException {

	public LoginIdInvalidException() {
		super("아이디는 5 ~ 12 글자 사이로 공백 없이 입력해주세요", HttpStatus.BAD_REQUEST);
	}
}
