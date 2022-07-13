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
import java.util.Optional;

import static com.efub.lakkulakku.global.constant.ResponseConstant.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResDto> login(@Valid @RequestBody LoginReqDto loginReqDto) throws Exception{
        Optional<User> userOpt = userService.findUserByEmail(loginReqDto);
        // TODO: ExceptionHandler 정한 후 에러 핸들링 추가 (유저가 존재하지 않는 경우)
//        if(user.isEmpty()) throw new NoSuchUserException("잘못된 형식입니다. 다시 입력해주세요.");
        User user = userOpt.get();

        if(!loginReqDto.getPassword().matches(user.getPassword())) {
            throw new Exception("잘못된 비밀번호입니다.");  // TODO: 에러 핸들링 후 코드 변경
        }

        // TODO: jwt 추가 후 data null -> token
        return ResponseEntity.ok(LoginResDto.builder().message(LOGIN_SUCCESS).data(null).build());
    }
}
