package com.ssafy.Mokkoji.core.model.exception;

import com.ssafy.Mokkoji.global.exception.MokkojiException;
import org.springframework.http.HttpStatus;

public class ContentEmptyException extends MokkojiException {

    public ContentEmptyException() {
        super("내용을 반드시 입력해주세요", HttpStatus.BAD_REQUEST);
    }
}
