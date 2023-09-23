package com.ssafy.Mokkoji.core.board.dto.response;

import com.ssafy.Mokkoji.core.board.domain.BoardImage;
import lombok.Data;

@Data
public class BoardImageResponse {

    private Long boardImageId;

    private String userFileName;

    private String storedFileName;

    public BoardImageResponse(BoardImage boardImage) {
        this.boardImageId = boardImage.getBoardImageId();
        this.userFileName = boardImage.getUserFileName();
        this.storedFileName = boardImage.getStoredFileName();
    }
}
