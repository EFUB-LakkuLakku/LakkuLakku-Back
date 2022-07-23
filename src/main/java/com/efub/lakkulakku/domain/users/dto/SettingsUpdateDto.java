package com.efub.lakkulakku.domain.users.dto;


import javax.validation.constraints.NotBlank;

public class SettingsUpdateDto {

    @NotBlank
    public String password;
}