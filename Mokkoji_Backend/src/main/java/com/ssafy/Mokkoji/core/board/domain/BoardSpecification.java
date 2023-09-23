package com.ssafy.Mokkoji.core.board.domain;

import com.ssafy.Mokkoji.core.user.domain.User;
import lombok.Getter;

import java.time.LocalDateTime;

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
        this.title = board.getTitle();
        this.nickname = user.getNickname();
        this.createdDate = board.getCreatedDate();
    }
}
