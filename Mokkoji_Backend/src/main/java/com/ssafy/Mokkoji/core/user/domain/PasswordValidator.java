package com.ssafy.Mokkoji.core.user.domain;

import org.springframework.stereotype.Component;

import com.ssafy.Mokkoji.core.user.exception.PasswordInvalidException;

@Component
public class PasswordValidator {

	private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";

	public void validatePassword(final String password) {
		if (password == null || !password.matches(PASSWORD_PATTERN)) {
			throw new PasswordInvalidException();
		}
	}
}
