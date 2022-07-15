package com.efub.lakkulakku.domain.user.controller;

import com.efub.lakkulakku.domain.user.dto.SignupReqDto;
import com.efub.lakkulakku.domain.user.entity.User;
import com.efub.lakkulakku.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1//users")
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @PostMapping("/signup")
    public User signup(@RequestBody SignupReqDto reqDto){
        return userService.signup(reqDto);
    }

    @GetMapping("/signup")
    public ResponseEntity<?> checkNicknameDuplicate(@RequestParam String nickname)
    {
        if(userService.checkNicknameDuplicate(nickname) == true)
        {
            return ResponseEntity.badRequest().build();
        }
        else {
            return ResponseEntity.ok("사용할 수 있는 닉네임입니다.");

        }
    }

}
