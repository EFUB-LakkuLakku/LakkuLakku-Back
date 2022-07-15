package com.efub.lakkulakku.domain.user.dto;

import com.efub.lakkulakku.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SettingsInfoDto {
    private String email;

    @Builder
    public SettingsInfoDto(User user) {

        this.email = user.getEmail();

    }
}