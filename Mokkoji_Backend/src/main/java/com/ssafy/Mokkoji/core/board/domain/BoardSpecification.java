package com.ssafy.Mokkoji.core.board.domain;

import java.time.LocalDateTime;

import com.ssafy.Mokkoji.core.user.domain.User;

import lombok.Getter;

@Getter
public class BoardSpecification {

	private Long boardId;
	private String title;
	private String nickname;
	private LocalDateTime createdDate;

	public BoardSpecification(
		final Board board,
		final User user
	) {
		this.boardId = board.getBoardId();
		this.title = board.getTitle().getTitle();
		this.nickname = user.getNickname().getValue();
		this.createdDate = board.getCreatedDate();
	}
}
