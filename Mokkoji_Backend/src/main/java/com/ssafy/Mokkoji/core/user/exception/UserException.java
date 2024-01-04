package com.ssafy.Mokkoji.core.user.exception;

import org.springframework.http.HttpStatus;

import com.ssafy.Mokkoji.global.exception.MokkojiException;

public class UserException extends MokkojiException {

	public UserException(HttpStatus httpStatus) {
		super(httpStatus);
	}

	public UserException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserException(String message, Throwable cause, HttpStatus httpStatus) {
		super(message, cause, httpStatus);
	}

	public UserException(String message) {
		super(message);
	}

	public UserException(String message, HttpStatus httpStatus) {
		super(message, httpStatus);
	}
}
