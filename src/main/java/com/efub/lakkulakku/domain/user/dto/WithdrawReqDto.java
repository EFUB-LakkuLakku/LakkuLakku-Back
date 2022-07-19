package com.efub.lakkulakku.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class WithdrawReqDto {

    @NotBlank
    private String nickname;

    @Builder
    public WithdrawReqDto(String nickname) {
        this.nickname = nickname;
    }
}
