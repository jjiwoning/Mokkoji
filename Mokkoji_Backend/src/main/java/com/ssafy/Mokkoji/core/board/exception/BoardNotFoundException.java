package com.ssafy.Mokkoji.core.board.exception;

import org.springframework.http.HttpStatus;

public class BoardNotFoundException extends BoardException {

    public BoardNotFoundException() {
        super("게시글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
    }
}
