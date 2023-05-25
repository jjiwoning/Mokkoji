package com.ssafy.enjoytrip.dto.response;

import com.ssafy.enjoytrip.domain.Board;
import com.ssafy.enjoytrip.domain.BoardImage;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class BoardDetailResponseDto {
    private Long boardId;
    private String title;
    private String content;
    private String nickname;
    private List<BoardImageResponseDto> imageList;
    private LocalDateTime createdDate;

    public BoardDetailResponseDto(Board board) {
        this.boardId = board.getBoardId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.nickname = board.getUser().getNickname();
        this.imageList = board.getBoardImages().stream().map(BoardImageResponseDto::new).collect(Collectors.toList());
        this.createdDate = board.getCreatedDate();
    }
}
