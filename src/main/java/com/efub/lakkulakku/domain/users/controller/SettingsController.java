package com.efub.lakkulakku.domain.users.controller;

import com.efub.lakkulakku.domain.users.dto.*;
import com.efub.lakkulakku.domain.users.service.*;
import com.efub.lakkulakku.domain.users.dto.UpdatePasswordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
