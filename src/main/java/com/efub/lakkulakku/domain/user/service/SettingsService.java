package com.efub.lakkulakku.domain.user.service;

import com.efub.lakkulakku.domain.user.dto.SettingsInfoDto;
import com.efub.lakkulakku.domain.user.dto.SettingsUpdateDto;
import com.efub.lakkulakku.domain.user.dto.UpdatePasswordDto;
import com.efub.lakkulakku.domain.user.exception.PasswordNotMatchedException;

public interface SettingsService {


    void updatePassword(String beforePassword, String afterPassword, String email) throws PasswordNotMatchedException;


}