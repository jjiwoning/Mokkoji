package com.ssafy.Mokkoji.core.user.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.util.StringUtils;

import com.ssafy.Mokkoji.core.user.exception.LoginIdInvalidException;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class LoginId {

	@Column(name = "login_id", unique = true)
	private String value;

	private LoginId(final String value) {
		valid(value);
		this.value = value;
	}

	private void valid(final String value) {
		if (StringUtils.containsWhitespace(value) || checkInvalidLength(value)) {
			throw new LoginIdInvalidException();
		}
	}

	private boolean checkInvalidLength(final String value) {
		return !(5 <= value.length() && value.length() <= 12);
	}

	public static LoginId from(final String value) {
		return new LoginId(value);
	}
}
