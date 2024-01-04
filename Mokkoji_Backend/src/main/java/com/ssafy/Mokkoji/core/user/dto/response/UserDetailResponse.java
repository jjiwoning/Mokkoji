package com.ssafy.Mokkoji.core.user.dto.response;

import com.ssafy.Mokkoji.core.user.domain.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class UserDetailResponse {

	private Long userId;

	private String loginId;

	private String nickname;

	private String mail;

	private String name;

	private String password;

	private String phoneNumber;

	public static UserDetailResponse of(final User user) {
		return new UserDetailResponse(
			user.getUserId(),
			user.getLoginId().getValue(),
			user.getNickname().getValue(),
			user.getMail().getValue(),
			user.getName(),
			user.getPassword().getValue(),
			user.getPhoneNumber().getValue()
		);
	}
}
