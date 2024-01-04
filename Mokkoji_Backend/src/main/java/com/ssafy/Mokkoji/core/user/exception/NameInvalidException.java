package com.ssafy.Mokkoji.core.user.exception;

import org.springframework.http.HttpStatus;

public class NameInvalidException extends UserException {

	public NameInvalidException() {
		super("이름은 2글자 이상으로 입력해주세요.", HttpStatus.BAD_REQUEST);
	}
}
