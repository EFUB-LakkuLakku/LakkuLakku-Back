package com.efub.lakkulakku.domain.users.controller;

import com.efub.lakkulakku.domain.users.dto.LoginReqDto;
import com.efub.lakkulakku.domain.users.dto.LoginResDto;
import com.efub.lakkulakku.domain.users.dto.SignupReqDto;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.exception.DuplicateEmailException;
import com.efub.lakkulakku.domain.users.exception.DuplicateNicknameException;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import com.efub.lakkulakku.domain.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.efub.lakkulakku.global.constant.ResponseConstant.LOGIN_SUCCESS;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class LoginController {
	private final UsersService usersService;
	private final UsersRepository usersRepository;


	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody SignupReqDto reqDto) {
		usersService.signup(reqDto);
		//나중에 토큰 방법 추가 후 토큰을 responseBody로 보내기
		return ResponseEntity.ok("성공적으로 가입되었습니다.");
	}

	@GetMapping("/signup/email")
	public ResponseEntity<?> checkEmailDuplicate(@RequestParam String email) {
		if (usersRepository.existsByEmail(email)) {
			throw new DuplicateEmailException();
		} else {
			return ResponseEntity.ok("사용할 수 있는 이메일입니다.");

		}
	}

	@GetMapping("/signup/nickname")
	public ResponseEntity<?> checkNicknameDuplicate(@RequestParam String nickname) {
		if (usersRepository.existsByNickname(nickname)) {
			throw new DuplicateNicknameException();
		} else {
			return ResponseEntity.ok("사용할 수 있는 닉네임입니다.");
		}
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResDto> login(@Valid @RequestBody LoginReqDto loginReqDto) {
		Users user = usersService.findUsersByEmail(loginReqDto);

		if (!loginReqDto.getPassword().matches(user.getPassword())) {
			throw new RuntimeException("잘못된 비밀번호입니다.");  // TODO: 에러 핸들링 후 코드 변경
		}

		// TODO: jwt 추가 후 data null -> token
		return ResponseEntity.ok(LoginResDto.builder().message(LOGIN_SUCCESS).data(null).build());
	}

}
