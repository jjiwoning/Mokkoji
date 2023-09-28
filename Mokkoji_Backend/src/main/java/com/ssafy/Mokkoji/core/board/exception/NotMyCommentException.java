package com.ssafy.Mokkoji.core.board.exception;

import org.springframework.http.HttpStatus;

public class NotMyCommentException extends BoardException {

    public NotMyCommentException() {
        super("본인의 댓글이 아닙니다.", HttpStatus.BAD_REQUEST);
    }
}
