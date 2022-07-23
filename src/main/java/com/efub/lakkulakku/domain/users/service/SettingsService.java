package com.efub.lakkulakku.domain.users.service;

import com.efub.lakkulakku.domain.users.dto.SettingsUpdateDto;

public interface SettingsService {

    void update(SettingsUpdateDto settingsUpdateDto, String username) throws Exception;

    void updatePassword(String beforePassword, String afterPassword, String email) throws Exception;


}