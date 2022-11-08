package com.efub.lakkulakku.domain.users.controller;

import com.efub.lakkulakku.domain.friend.exception.UserNotFoundException;
import com.efub.lakkulakku.domain.users.dto.*;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import com.efub.lakkulakku.domain.users.service.AuthUsers;
import com.efub.lakkulakku.domain.users.service.CustomOAuth2UserService;
import com.efub.lakkulakku.domain.users.service.UsersService;
import com.efub.lakkulakku.global.config.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/authusers")
public class AuthUserController {
	private final UsersRepository usersRepository;
	//private final RedisService redisService;
	private final UsersService usersService;
	private final JwtProvider jwtTokenProvider;
	private final CustomOAuth2UserService oauth2UserService;

	//todo load user

	@PostMapping("/login")
	public ResponseEntity<LoginResDto> login(@RequestBody LoginReqDto loginDto){

		UUID memberId = usersRepository.findByEmail(loginDto.getEmail()).get().getId();
		LoginInfoDto loginInfoDto = usersService.login(loginDto.getEmail(), loginDto.getPassword());
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Set-Cookie",usersService.generateCookie("refreshToken", loginInfoDto.getRefreshToken()).toString());

		return new ResponseEntity<LoginResDto>(loginInfoDto.toLoginResDto(), responseHeaders, HttpStatus.CREATED);

	}

	@PostMapping("/reissue")
	public ResponseEntity<LoginResDto> reIssue(@RequestBody EmailGetReqDto reqDto, HttpServletRequest request, @CookieValue(value = "refreshToken", required = false) Cookie rCookie) {
		String refreshToken = rCookie.getValue();
		System.out.println("refreshToken = " + refreshToken);
		if(refreshToken == null)
		{
			throw new UserNotFoundException();
		}
		LoginInfoDto responseDto = usersService.reIssueAccessToken(reqDto.getEmail(), refreshToken);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Set-Cookie",usersService.generateCookie("refreshToken", responseDto.getRefreshToken()).toString());
		return new ResponseEntity<LoginResDto>(responseDto.toLoginResDto(), responseHeaders, HttpStatus.CREATED);
	}
}