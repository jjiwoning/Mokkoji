package com.ssafy.Mokkoji.core.board.dto.response;

import com.ssafy.Mokkoji.core.board.domain.Board;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardListResponseDto {

    private Long boardId;
    private String title;
    private String nickname;
    private LocalDateTime createdDate;

    public BoardListResponseDto(Board board) {
        this.boardId = board.getBoardId();
        this.title = board.getTitle();
        this.nickname = board.getUser().getNickname();
        this.createdDate=board.getCreatedDate();
    }
}
