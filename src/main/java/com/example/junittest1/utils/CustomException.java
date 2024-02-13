package com.example.junittest1.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {

    private String message;
    private ErrorCode errorCode;

}
