package com.efub.lakkulakku.domain.friend.dto;


import com.efub.lakkulakku.domain.profile.entity.Profile;
import com.efub.lakkulakku.domain.users.entity.Users;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FriendResDto {
	private Long uid;
	private String profileImageUrl;
	private String nickname;


	@Builder
	public FriendResDto(Users user) {
		this.uid = user.getUid();
		this.profileImageUrl = user.getProfile().getFile().getUrl();
		this.nickname = user.getNickname();
	}


}

