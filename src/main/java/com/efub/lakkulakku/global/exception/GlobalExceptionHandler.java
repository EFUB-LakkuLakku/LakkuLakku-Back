package com.efub.lakkulakku.global.exception;

import com.efub.lakkulakku.domain.diary.exception.DuplicateDiaryException;
import com.efub.lakkulakku.domain.file.exception.FileExtenstionException;
import com.efub.lakkulakku.domain.file.exception.S3IOException;
import com.efub.lakkulakku.domain.users.exception.DuplicateEmailException;
import com.efub.lakkulakku.domain.users.exception.DuplicateNicknameException;
import com.efub.lakkulakku.domain.users.exception.UserNotFoundException;
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

    // 존재하지 않는 유저
    @ExceptionHandler(UserNotFoundException.class)
    protected final ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
        final ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .code(ErrorCode.USER_NOT_FOUND)
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // io 예외
    @ExceptionHandler(S3IOException.class)
    protected final ResponseEntity<ErrorResponse> handleS3IOException(S3IOException e) {
        final ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .code(ErrorCode.S3_UPLOAD_FAILURE)
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // 파일 형식 잘못된 경우
    @ExceptionHandler(FileExtenstionException.class)
    protected final ResponseEntity<ErrorResponse> handleFileExtenstionException(FileExtenstionException e) {
        final ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .code(ErrorCode.FILE_UPLOAD_FAILURE)
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
