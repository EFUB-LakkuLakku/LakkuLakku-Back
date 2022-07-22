package com.efub.lakkulakku.domain.user.controller;

import com.efub.lakkulakku.domain.user.dto.LoginReqDto;
import com.efub.lakkulakku.domain.user.dto.LoginResDto;
import com.efub.lakkulakku.domain.user.entity.User;
import com.efub.lakkulakku.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.efub.lakkulakku.global.constant.ResponseConstant.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResDto> login(@Valid @RequestBody LoginReqDto loginReqDto) {
        User user = userService.findUserByEmail(loginReqDto);

        if(!loginReqDto.getPassword().matches(user.getPassword())) {
            throw new RuntimeException("잘못된 비밀번호입니다.");  // TODO: 에러 핸들링 후 코드 변경
        }

        // TODO: jwt 추가 후 data null -> token
        return ResponseEntity.ok(LoginResDto.builder().message(LOGIN_SUCCESS).data(null).build());
    }
}
