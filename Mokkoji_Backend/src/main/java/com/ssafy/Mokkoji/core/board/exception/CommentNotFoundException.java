package com.ssafy.Mokkoji.core.board.exception;

import org.springframework.http.HttpStatus;

public class CommentNotFoundException extends BoardException {

    public CommentNotFoundException() {
        super("댓글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
    }
}
