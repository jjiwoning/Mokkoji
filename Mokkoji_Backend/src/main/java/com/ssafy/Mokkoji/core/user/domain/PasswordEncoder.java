package com.ssafy.Mokkoji.core.user.domain;

public interface PasswordEncoder {

	String encode(final String password);

	boolean matches(final String rawPassword, final String encodedPassword);
}
