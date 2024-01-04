package com.ssafy.Mokkoji.core.user.dto.response;

import com.ssafy.Mokkoji.core.user.domain.User;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserResponse {
	private Long userId;
	private String nickname;

	private UserResponse(final User user) {
		this.userId = user.getUserId();
		this.nickname = user.getNickname().getValue();
	}

	public static UserResponse from(final User user) {
		return new UserResponse(user);
	}
}
