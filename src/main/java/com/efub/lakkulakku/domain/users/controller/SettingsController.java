package com.efub.lakkulakku.domain.users.controller;

import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.service.*;
import com.efub.lakkulakku.domain.users.dto.PasswordUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.efub.lakkulakku.global.constant.ResponseConstant.PASSWORD_CHANGE_SUCCESS;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/settings")
public class SettingsController {

    private final SettingsServiceImpl settingsServiceImpl;

    @PutMapping()
    public ResponseEntity<String> updatePassword(@AuthUsers Users user, @Valid @RequestBody PasswordUpdateDto passwordUpdateDto){
        settingsServiceImpl.updatePassword(user, passwordUpdateDto);
        return ResponseEntity.ok(PASSWORD_CHANGE_SUCCESS);
    }



}
