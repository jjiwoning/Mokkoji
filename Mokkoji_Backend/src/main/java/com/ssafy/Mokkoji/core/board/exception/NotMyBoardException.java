package com.ssafy.Mokkoji.core.board.exception;

import org.springframework.http.HttpStatus;

public class NotMyBoardException extends BoardException {

    public NotMyBoardException() {
        super("본인의 게시글이 아닙니다.", HttpStatus.BAD_REQUEST);
    }
}
