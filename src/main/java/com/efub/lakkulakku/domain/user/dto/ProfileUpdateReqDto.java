package com.efub.lakkulakku.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class ProfileUpdateReqDto {

    @NotBlank
    private String nickname;

    private String image;
    private String bio;

    @Builder
    public ProfileUpdateReqDto(String nickname, String image, String bio) {
        this.nickname = nickname;
        this.image = image;
        this.bio = bio;
    }
}
