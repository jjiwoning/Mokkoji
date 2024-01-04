package com.ssafy.Mokkoji.core.trip.domain;

import java.time.LocalDateTime;

import com.ssafy.Mokkoji.core.user.domain.User;

import lombok.Getter;

@Getter
public class TeamCommentSpecification {

	private Long teamCommentId;

	private String content;

	private Long userId;

	private String nickname;

	private LocalDateTime createdDate;

	public TeamCommentSpecification(
		final TeamComment teamComment,
		final User user
	) {
		this.teamCommentId = teamComment.getTeamCommentId();
		this.content = teamComment.getContent();
		this.userId = user.getUserId();
		this.nickname = user.getNickname().getValue();
		this.createdDate = teamComment.getCreatedDate();
	}
}
