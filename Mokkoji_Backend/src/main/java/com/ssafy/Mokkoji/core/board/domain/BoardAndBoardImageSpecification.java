package com.ssafy.Mokkoji.core.board.domain;

import com.ssafy.Mokkoji.core.user.domain.User;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class BoardAndBoardImageSpecification {

    private Long boardId;
    private String title;
    private String content;
    private String nickname;
    private List<BoardImage> imageList;
    private LocalDateTime createdDate;

    public BoardAndBoardImageSpecification(
            final Board board,
            final User user,
            final List<BoardImage> imageList
    ) {
        this.boardId = board.getBoardId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.nickname = user.getNickname();
        this.imageList = new ArrayList<>(imageList);
        this.createdDate = board.getCreatedDate();
    }
}
