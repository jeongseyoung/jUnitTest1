package com.example.junittest1.utils;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    NOT_FOUND_ZZZ(HttpStatus.NOT_FOUND, "NOT FOUND!!!");

    private HttpStatus httpStatus;
    private String message;
}
