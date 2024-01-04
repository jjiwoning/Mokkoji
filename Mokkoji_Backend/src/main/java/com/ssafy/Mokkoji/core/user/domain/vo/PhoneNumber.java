package com.ssafy.Mokkoji.core.user.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.ssafy.Mokkoji.core.user.exception.PhoneNumberInvalidException;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class PhoneNumber {

	private static final String PHONE_NUMBER_PATTERN = "(01[016789])(\\d{3,4})(\\d{4})";

	@Column(name = "phone_number")
	private String value;

	private PhoneNumber(final String value) {
		valid(value);
		this.value = value;
	}

	private void valid(final String value) {
		if (value == null || !value.matches(PHONE_NUMBER_PATTERN)) {
			throw new PhoneNumberInvalidException();
		}
	}

	public static PhoneNumber from(final String value) {
		return new PhoneNumber(value);
	}
}
