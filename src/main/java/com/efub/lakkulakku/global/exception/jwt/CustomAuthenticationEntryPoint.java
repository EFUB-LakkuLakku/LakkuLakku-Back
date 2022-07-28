package com.efub.lakkulakku.global.exception.jwt;

import com.efub.lakkulakku.global.exception.ErrorCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Getter
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	public static final ObjectMapper mapper = new ObjectMapper();

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		String exception = (String) request.getAttribute("exception");
		setResponse(response);
		BasicResponse exceptionDto = BasicResponse.builder()
				.status(HttpStatus.FORBIDDEN)
				.code(ErrorCode.TOKEN_VALIDATE_FAILURE)
				.message(exception)
				.build();

		response.getWriter().print(convertObjectToJson(exceptionDto));
	}

	private void setResponse(HttpServletResponse response) {
		response.setStatus(HttpStatus.FORBIDDEN.value());
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
	}

	public String convertObjectToJson(Object object) throws JsonProcessingException {
		if (object == null)
			return null;
		return mapper.writeValueAsString(object);
	}
}
