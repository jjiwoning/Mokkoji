package com.ssafy.Mokkoji.core.board.dto.response;

import com.ssafy.Mokkoji.core.board.domain.BoardAndBoardImageSpecification;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class BoardResponse {
    private Long boardId;
    private String title;
    private String content;
    private String nickname;
    private List<BoardImageResponse> imageList;
    private LocalDateTime createdDate;

    public BoardResponse(BoardAndBoardImageSpecification boardAndBoardImageSpecification) {
        this.boardId = boardAndBoardImageSpecification.getBoardId();
        this.title = boardAndBoardImageSpecification.getTitle();
        this.content = boardAndBoardImageSpecification.getContent();
        this.nickname = boardAndBoardImageSpecification.getNickname();
        this.imageList = boardAndBoardImageSpecification.getImageList().stream().map(BoardImageResponse::new).collect(Collectors.toList());
        this.createdDate = boardAndBoardImageSpecification.getCreatedDate();
    }
}
