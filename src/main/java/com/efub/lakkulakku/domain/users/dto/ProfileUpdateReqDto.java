package com.efub.lakkulakku.domain.users.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class ProfileUpdateReqDto {

    private String image;
    private String bio;

    @Builder
    public ProfileUpdateReqDto(String image, String bio) {
        this.image = image;
        this.bio = bio;
    }
}
