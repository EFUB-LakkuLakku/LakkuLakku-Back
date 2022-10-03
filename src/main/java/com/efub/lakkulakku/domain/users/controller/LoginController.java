package com.efub.lakkulakku.domain.users.controller;

import com.efub.lakkulakku.domain.friend.exception.UserNotFoundException;
import com.efub.lakkulakku.domain.users.dao.CertificationDao;
import com.efub.lakkulakku.domain.users.dto.*;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.exception.DuplicateEmailException;
import com.efub.lakkulakku.domain.users.exception.DuplicateNicknameException;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import com.efub.lakkulakku.domain.users.service.AuthUsers;
import com.efub.lakkulakku.domain.users.service.MailSendService;
import com.efub.lakkulakku.domain.users.service.UsersService;
import com.efub.lakkulakku.global.exception.ErrorCode;
import com.efub.lakkulakku.global.exception.jwt.BasicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.efub.lakkulakku.global.constant.ResponseConstant.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class LoginController {
	private final UsersService usersService;
	private final UsersRepository usersRepository;
	private final MailSendService mailSendService;
	private final CertificationDao certificationDao;

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@Valid @RequestBody SignupReqDto reqDto) {
		usersService.signup(reqDto);
		return ResponseEntity.ok(SIGNUP_SUCCESS);
	}

	@PostMapping("/signup/email")
	public ResponseEntity<?> checkEmailDuplicate(@Valid @RequestBody String email) {
		if (usersRepository.existsByEmail(email)) {
			throw new DuplicateEmailException();
		} else {
			return ResponseEntity.ok(AVAILABLE_EMAIL);

		}
	}

	@PostMapping("/signup/nickname")
	public ResponseEntity<?> checkNicknameDuplicate(@Valid @RequestBody UserGetReqDto reqDto) {
		if (usersRepository.existsByNickname(reqDto.getNickname())) {
			throw new DuplicateNicknameException();
		} else {
			return ResponseEntity.ok(AVAILABLE_NICKNAME);
		}
	}

	@PostMapping("/certification/sends")
	public ResponseEntity<?> sendEmail(@Valid @RequestBody EmailGetReqDto reqDto){
		Users user = usersRepository.findByEmail(reqDto.getEmail()).orElseThrow(()
				-> new UserNotFoundException());
		String certiCode = usersService.getTempString();
		certificationDao.createEmailCertification(reqDto.getEmail(), certiCode);
		mailSendService.sendMail(mailSendService.createMail(certiCode, reqDto.getEmail()));
		return ResponseEntity.ok(SEND_EMAIL_SUCCESS);
	}

	@PostMapping("/certification/comfirms")
	public ResponseEntity<?> confirmTempString(@Valid @RequestBody CertificationReqDto reqDto){
		mailSendService.verifyEmail(reqDto);
		LoginInfoDto loginInfoDto = usersService.provideToken(reqDto.getEmail());
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Set-Cookie",usersService.generateCookie("refreshToken", loginInfoDto.getRefreshToken()).toString());
		return new ResponseEntity<LoginResDto>(loginInfoDto.toLoginResDto(), responseHeaders, HttpStatus.CREATED);
	}

	@PostMapping("/certification/new-password")
	public ResponseEntity<?> EnterNewPassword(@AuthUsers Users user, @RequestBody NewPwdReqDto reqDto)
	{
		usersService.newPassword(user, reqDto);
		return ResponseEntity.ok(PASSWORD_CHANGE_SUCCESS);
	}


	@PostMapping("/login")
	public ResponseEntity<LoginResDto> login(@RequestBody LoginReqDto loginDto) {
		LoginInfoDto loginInfoDto = usersService.login(loginDto.getEmail(), loginDto.getPassword());
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Set-Cookie",usersService.generateCookie("refreshToken", loginInfoDto.getRefreshToken()).toString());
		return new ResponseEntity<LoginResDto>(loginInfoDto.toLoginResDto(), responseHeaders, HttpStatus.CREATED);
	}


	@PostMapping("/re-issue")
	public ResponseEntity<LoginResDto> reIssue(@RequestBody EmailGetReqDto reqDto, HttpServletRequest request, @CookieValue(value = "refreshToken", required = false) Cookie rCookie) {
		String refreshToken = rCookie.getValue();
		System.out.println("refreshToken = " + refreshToken);
		if(refreshToken == null)
		{
			throw new UserNotFoundException();//나중에 커스텀
		}
		LoginInfoDto responseDto = usersService.reIssueAccessToken(reqDto.getEmail(), refreshToken);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Set-Cookie",usersService.generateCookie("refreshToken", responseDto.getRefreshToken()).toString());
		return new ResponseEntity<LoginResDto>(responseDto.toLoginResDto(), responseHeaders, HttpStatus.CREATED);
	}

	@GetMapping("/logout")
	public ResponseEntity<BasicResponse> logout(@AuthUsers Users user, HttpServletRequest request) {
		String accessToken = request.getHeader("Authorization").substring(7);
		usersService.logout(user.getEmail(), accessToken);
		BasicResponse response = new BasicResponse(HttpStatus.OK, ErrorCode.LOGOUT_SUCCESS, "LOGOUT_SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
