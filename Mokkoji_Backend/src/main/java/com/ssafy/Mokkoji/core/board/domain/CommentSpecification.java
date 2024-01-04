package com.ssafy.Mokkoji.core.board.domain;

import java.time.LocalDateTime;

import com.ssafy.Mokkoji.core.user.domain.User;

import lombok.Getter;

@Getter
public class CommentSpecification {

	private Long commentId;

	private String content;

	private Long userId;

	private String nickname;

	private LocalDateTime createdDate;

	public CommentSpecification(
		final Comment comment,
		final User user
	) {
		this.commentId = comment.getCommentId();
		this.content = comment.getContent().getContent();
		this.userId = user.getUserId();
		this.nickname = user.getNickname().getValue();
		this.createdDate = comment.getCreatedDate();
	}
}
