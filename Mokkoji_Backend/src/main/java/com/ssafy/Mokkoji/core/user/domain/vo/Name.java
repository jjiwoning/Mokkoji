package com.ssafy.Mokkoji.core.user.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.util.StringUtils;

import com.ssafy.Mokkoji.core.user.exception.NameInvalidException;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class Name {

	@Column(name = "name")
	private String value;

	private Name(final String value) {
		valid(value);
		this.value = value;
	}

	private void valid(final String value) {
		if (value == null || StringUtils.containsWhitespace(value) || checkInvalidLength(value)) {
			throw new NameInvalidException();
		}
	}

	private boolean checkInvalidLength(final String value) {
		return !(2 <= value.length());
	}

	public static Name from(final String value) {
		return new Name(value);
	}
}
