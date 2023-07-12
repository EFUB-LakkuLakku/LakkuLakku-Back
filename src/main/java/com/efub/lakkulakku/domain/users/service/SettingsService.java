package com.efub.lakkulakku.domain.users.service;

import com.efub.lakkulakku.domain.users.dto.PasswordUpdateDto;
import com.efub.lakkulakku.domain.users.entity.Users;

public interface SettingsService {

    void updatePassword(Users user, PasswordUpdateDto updatePwDto);

}