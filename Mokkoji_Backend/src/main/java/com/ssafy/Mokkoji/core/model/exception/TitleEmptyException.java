package com.ssafy.Mokkoji.core.model.exception;

import com.ssafy.Mokkoji.global.exception.MokkojiException;
import org.springframework.http.HttpStatus;

public class TitleEmptyException extends MokkojiException {

    public TitleEmptyException() {
        super("제목을 반드시 입력해주세요", HttpStatus.BAD_REQUEST);
    }
}
