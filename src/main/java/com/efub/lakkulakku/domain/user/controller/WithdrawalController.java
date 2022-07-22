package com.efub.lakkulakku.domain.user.controller;

import com.efub.lakkulakku.domain.user.dto.LoginResDto;
import com.efub.lakkulakku.domain.user.dto.WithdrawReqDto;
import com.efub.lakkulakku.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.efub.lakkulakku.global.constant.ResponseConstant.WITHDRAW_SUCCESS;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class WithdrawalController {

    private final UserService userService;

    @PostMapping("/withdrawal")
    public ResponseEntity<LoginResDto> withdrawal(@Valid @RequestBody WithdrawReqDto withdrawReqDto) {
        userService.deleteUser(withdrawReqDto);
        return ResponseEntity.ok(LoginResDto.builder().message(WITHDRAW_SUCCESS).build());
    }
}
