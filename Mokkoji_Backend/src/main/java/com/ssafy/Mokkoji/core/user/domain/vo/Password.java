package com.ssafy.Mokkoji.core.user.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.ssafy.Mokkoji.core.user.domain.PasswordEncoder;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class Password {

	@Column(name = "password")
	private String value;

	private Password(final String encodedPassword) {
		this.value = encodedPassword;
	}

	public static Password of(final String rawPassword, final PasswordEncoder passwordEncoder) {
		return new Password(passwordEncoder.encode(rawPassword));
	}

	public static Password from(final String encodedPassword) {
		return new Password(encodedPassword);
	}

	public boolean matches(final String rawPassword, final PasswordEncoder passwordEncoder) {
		return passwordEncoder.matches(rawPassword, this.value);
	}
}
