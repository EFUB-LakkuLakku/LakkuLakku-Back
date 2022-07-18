package com.efub.lakkulakku.global.exception;

import com.efub.lakkulakku.domain.diary.exception.DuplicateDiaryException;
import com.efub.lakkulakku.domain.user.exception.DuplicateEmailException;
import com.efub.lakkulakku.domain.user.exception.DuplicateNicknameException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Diary 관련 error handler
    @ExceptionHandler(DuplicateDiaryException.class)
    protected final ResponseEntity<ErrorResponse> handleDuplicateDiaryException(DuplicateDiaryException e) {
        final ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .code(ErrorCode.DUPLICATE_DIARY_DATE)
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // 기본적인 RunTimeException handler
    @ExceptionHandler(RuntimeException.class)
    protected final ResponseEntity<ErrorResponse> handleRunTimeException(RuntimeException e) {
        final ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .code(ErrorCode.DUPLICATE_DIARY_DATE)
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    protected final ResponseEntity<ErrorResponse> handleDuplicateEmailException(DuplicateEmailException e) {
        final ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .code(ErrorCode.DUPLICATE_EMAIL)
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(DuplicateNicknameException.class)
    protected final ResponseEntity<ErrorResponse> handleDuplicateNicknameException(DuplicateNicknameException e) {
        final ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .code(ErrorCode.DUPLICATE_NICKNAME)
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
