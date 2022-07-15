package com.efub.lakkulakku.domain.user.service;

import com.efub.lakkulakku.domain.user.dto.SettingsInfoDto;
import com.efub.lakkulakku.domain.user.dto.SettingsUpdateDto;
import com.efub.lakkulakku.domain.user.dto.UpdatePasswordDto;

public interface SettingsService {

    void update(SettingsUpdateDto settingsUpdateDto, String username) throws Exception;

    void updatePassword(String beforePassword, String afterPassword, String email) throws Exception;


}