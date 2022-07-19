package com.efub.lakkulakku.domain.user.controller;

import com.efub.lakkulakku.domain.user.dto.SignupReqDto;
import com.efub.lakkulakku.domain.user.exception.DuplicateEmailException;
import com.efub.lakkulakku.domain.user.exception.DuplicateNicknameException;
import com.efub.lakkulakku.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupReqDto reqDto) {
        userService.signup(reqDto);
        //나중에 토큰 responseBody로 보내기
        return ResponseEntity.ok("성공적으로 가입되었습니다.");
    }

    @GetMapping("/signup/email")
    public ResponseEntity<?> checkEmailDuplicate(@RequestParam String email) {
        if (userService.checkEmailDuplicate(email) == true) {
            throw new DuplicateEmailException();
        } else {
            return ResponseEntity.ok("사용할 수 있는 이메일입니다.");

        }
    }

    @GetMapping("/signup/nickname")
    public ResponseEntity<?> checkNicknameDuplicate(@RequestParam String nickname) {
        if (userService.checkNicknameDuplicate(nickname) == true) {
            throw new DuplicateNicknameException();
        } else {
            return ResponseEntity.ok("사용할 수 있는 닉네임입니다.");

        }
    }

}
