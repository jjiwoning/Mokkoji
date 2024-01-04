package com.ssafy.Mokkoji.core.user.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.ssafy.Mokkoji.core.user.exception.MailInvalidException;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class Mail {

	private static final String MAIL_PATTERN = "^[a-zA-Z\\d._%+-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$";

	@Column(name = "mail")
	private String value;

	private Mail(final String value) {
		valid(value);
		this.value = value;
	}

	private void valid(final String value) {
		if (value == null || !value.matches(MAIL_PATTERN)) {
			throw new MailInvalidException();
		}
	}

	public static Mail from(final String value) {
		return new Mail(value);
	}
}
