package com.ssafy.Mokkoji.core.user.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.util.StringUtils;

import com.ssafy.Mokkoji.core.user.exception.NickNameInvalidException;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class NickName {

	@Column(name = "nick_name")
	private String value;

	private NickName(final String value) {
		valid(value);
		this.value = value;
	}

	private void valid(final String value) {
		if (StringUtils.containsWhitespace(value) || checkInvalidLength(value)) {
			throw new NickNameInvalidException();
		}
	}

	private boolean checkInvalidLength(final String value) {
		return !(2 <= value.length() && value.length() <= 12);
	}

	public static NickName from(final String value) {
		return new NickName(value);
	}
}
