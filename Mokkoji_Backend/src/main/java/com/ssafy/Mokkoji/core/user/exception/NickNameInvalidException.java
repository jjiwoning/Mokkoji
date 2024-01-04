package com.ssafy.Mokkoji.core.user.exception;

import org.springframework.http.HttpStatus;

public class NickNameInvalidException extends UserException {

	public NickNameInvalidException() {
		super("닉네임은 2 ~ 12 글자 사이로 공백 없이 입력해주세요", HttpStatus.BAD_REQUEST);
	}
}
