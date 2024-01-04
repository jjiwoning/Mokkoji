package com.ssafy.Mokkoji.core.user.exception;

import org.springframework.http.HttpStatus;

public class PhoneNumberInvalidException extends UserException {

	public PhoneNumberInvalidException() {
		super("올바른 휴대폰 번호를 입력해주세요.", HttpStatus.BAD_REQUEST);
	}
}
