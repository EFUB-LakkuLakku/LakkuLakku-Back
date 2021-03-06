package com.efub.lakkulakku.domain.users.dto;

import com.efub.lakkulakku.domain.users.entity.Users;
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

    public ProfileUpdateResDto(Users user){
        this.id = user.getId();
        this.profileImageUrl = user.getProfile().getFile().getUrl();
        this.nickname = user.getNickname();
        this.bio = user.getProfile().getBio();
    }
}
