package com.ssafy.Mokkoji.core.user.exception;

import org.springframework.http.HttpStatus;

public class MailInvalidException extends UserException {

	public MailInvalidException() {
		super("올바른 이메일 형식으로 입력해주세요.", HttpStatus.BAD_REQUEST);
	}
}
