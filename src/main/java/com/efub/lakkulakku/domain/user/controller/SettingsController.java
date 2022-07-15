package com.efub.lakkulakku.domain.user.controller;

import com.efub.lakkulakku.domain.user.dto.*;
import com.efub.lakkulakku.domain.user.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
public class SettingsController {

    @PutMapping("/api/v1/settings")
    @ResponseStatus(HttpStatus.OK)
    public void updateBasicInfo(@Valid @RequestBody SettingsUpdateDto settingUpdateDto) throws Exception {
        //SettingsService.update(settingUpdateDto, SecurityUtil.getLoginUsername());
    }

    public void updatePassword(@Valid @RequestBody UpdatePasswordDto updatePasswordDto) throws Exception {
        //SettingsService.updatePassword(updatePasswordDto.checkPassword(),updatePasswordDto.toBePassword(),
        //                                           SecurityUtil.getLoginUsername());
    }


}
