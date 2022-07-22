package com.efub.lakkulakku.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.CONFLICT;


@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Common
    DUPLICATE_DIARY_DATE(CONFLICT, "C0001", "DUPLICATE_DIARY_EXISTS"),
    DUPLICATE_NICKNAME(CONFLICT, "C0002", "DUPLICATE_NICKNAME_EXISTS"),
    DUPLICATE_EMAIL(CONFLICT, "C0003", "DUPLICATE_EMAIL_EXISTS");


    // Standard

    // Exception

    private final HttpStatus status;
    private final String code;
    private final String message;
}
