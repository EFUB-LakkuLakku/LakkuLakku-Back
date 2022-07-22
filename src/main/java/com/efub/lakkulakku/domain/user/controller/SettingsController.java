package com.efub.lakkulakku.domain.user.controller;

import com.efub.lakkulakku.domain.user.dto.*;
import com.efub.lakkulakku.domain.user.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.efub.lakkulakku.domain.user.exception.PasswordNotMatchedException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/settings")
@RequiredArgsConstructor
public class SettingsController {

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void updatePassword(@Valid @RequestBody UpdatePasswordDto updatePasswordDto) throws PasswordNotMatchedException {
        //SettingsService.updatePassword(updatePasswordDto.beforePassword(),updatePasswordDto.afterPassword(),
        //                                           SecurityUtil.getLoginUsername());
    }


}
