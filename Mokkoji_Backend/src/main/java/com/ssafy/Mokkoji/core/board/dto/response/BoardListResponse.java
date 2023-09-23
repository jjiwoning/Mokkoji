package com.ssafy.Mokkoji.core.board.dto.response;

import com.ssafy.Mokkoji.core.board.domain.BoardSpecification;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardListResponse {

    private Long boardId;
    private String title;
    private String nickname;
    private LocalDateTime createdDate;

    public BoardListResponse(BoardSpecification boardSpecification) {
        this.boardId = boardSpecification.getBoardId();
        this.title = boardSpecification.getTitle();
        this.nickname = boardSpecification.getNickname();
        this.createdDate=boardSpecification.getCreatedDate();
    }
}
