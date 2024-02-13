package com.example.junittest1.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager extends RuntimeException {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> CustomExceptionHanndler(CustomException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus()).body(e.getErrorCode().name() + " 입니다!");
    }
}
