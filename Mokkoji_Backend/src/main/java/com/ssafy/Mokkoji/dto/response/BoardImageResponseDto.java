package com.ssafy.Mokkoji.dto.response;

import com.ssafy.Mokkoji.domain.BoardImage;
import lombok.Data;

@Data
public class BoardImageResponseDto {

    private Long boardImageId;

    private String userFileName;

    private String storedFileName;

    public BoardImageResponseDto(BoardImage boardImage) {
        this.boardImageId = boardImage.getBoardImageId();
        this.userFileName = boardImage.getUserFileName();
        this.storedFileName = boardImage.getStoredFileName();
    }
}
