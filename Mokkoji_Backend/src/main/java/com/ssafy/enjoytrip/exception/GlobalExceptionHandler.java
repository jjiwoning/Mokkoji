package com.ssafy.enjoytrip.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse exceptionHandle(NotFoundException e) {
        return ErrorResponse.builder().code("404").message(e.getMessage()).build();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse validExceptionHandle(MethodArgumentNotValidException e) {
        ErrorResponse response = ErrorResponse.builder().code("400").message(e.getMessage()).build();

        for (FieldError fieldError : e.getFieldErrors()) {
            response.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return response;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class, NotImageException.class})
    public ErrorResponse illegalArgsExceptionHandle(RuntimeException e) {
        return ErrorResponse.builder().code("400").message(e.getMessage()).build();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(LoginException.class)
    public ErrorResponse illegalArgsExceptionHandle(LoginException e) {
        return ErrorResponse.builder().code("200").message(e.getMessage()).build();
    }
}
