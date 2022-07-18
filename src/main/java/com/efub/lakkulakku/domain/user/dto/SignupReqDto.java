package com.efub.lakkulakku.domain.user.dto;

import com.efub.lakkulakku.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignupReqDto {
    private String email;
    private String password;
    private String nickname;

    @Builder
    public SignupReqDto(String email, String password, String nickname)
    {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public User toEntity()
    {
        return User.builder()
                .uid(System.currentTimeMillis())
                .email(email)
                .password(password)
                .nickname(nickname)
                .build();
    }

}
