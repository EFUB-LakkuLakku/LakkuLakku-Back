package com.efub.lakkulakku.domain.user.dto;

import com.efub.lakkulakku.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class ProfileUpdateResDto {
    private UUID id;
    private String profileImageUrl;
    private String nickname;
    private String bio;

    public ProfileUpdateResDto(User user){
        this.id = user.getId();
        this.profileImageUrl = user.getProfile().getFile().getUrl();
        this.nickname = user.getNickname();
        this.bio = user.getProfile().getBio();
    }
}
