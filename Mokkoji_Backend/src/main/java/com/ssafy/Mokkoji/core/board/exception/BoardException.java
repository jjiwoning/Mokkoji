package com.ssafy.Mokkoji.core.board.exception;

import com.ssafy.Mokkoji.global.exception.MokkojiException;
import org.springframework.http.HttpStatus;

public class BoardException extends MokkojiException {

    public BoardException(HttpStatus httpStatus) {
        super(httpStatus);
    }

    public BoardException(String message, Throwable cause) {
        super(message, cause);
    }

    public BoardException(String message, Throwable cause, HttpStatus httpStatus) {
        super(message, cause, httpStatus);
    }

    public BoardException(String message) {
        super(message);
    }

    public BoardException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
