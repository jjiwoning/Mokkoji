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
    private List<BoardImage> boardImages;
    private LocalDateTime createdDate;

    public BoardAndBoardImageSpecification(
            Board board,
            User user,
            List<BoardImage> boardImages
    ) {
        this.boardId = board.getBoardId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.nickname = user.getNickname();
        this.boardImages = new ArrayList<>(boardImages);
        this.createdDate = board.getCreatedDate();
    }
}
