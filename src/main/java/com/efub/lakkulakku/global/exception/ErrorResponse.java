package com.efub.lakkulakku.global.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class ErrorResponse {
	private HttpStatus status;
	private ErrorCode code;
	private String message;
	private LocalDateTime date = LocalDateTime.now();

	@Builder
	public ErrorResponse(HttpStatus status, ErrorCode code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}

	public static ResponseEntity<ErrorResponse> toErrorResponseEntity(ErrorCode code, String message) {
		ErrorResponse response = ErrorResponse.builder()
				.status(code.getStatus())
				.code(code)
				.message(message)
				.build();

		return ResponseEntity.status(response.getStatus()).body(response);
	}

}
