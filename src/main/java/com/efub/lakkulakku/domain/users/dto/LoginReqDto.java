package com.efub.lakkulakku.domain.users.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class LoginReqDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @Builder
    public LoginReqDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
