package com.efub.lakkulakku.domain.users.controller;

import com.efub.lakkulakku.domain.user.dto.WithdrawReqDto;
import com.efub.lakkulakku.domain.users.dto.LoginResDto;
import com.efub.lakkulakku.domain.users.service.UsersService;
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

    private final UsersService usersService;

    @PostMapping("/withdrawal")
    public ResponseEntity<LoginResDto> withdrawal(@Valid @RequestBody WithdrawReqDto withdrawReqDto) {
        usersService.deleteUser(withdrawReqDto);
        return ResponseEntity.ok(LoginResDto.builder().message(WITHDRAW_SUCCESS).build());
    }
}
