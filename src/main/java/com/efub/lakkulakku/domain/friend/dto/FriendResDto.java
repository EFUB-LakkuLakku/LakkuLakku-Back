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
	private Profile profile_image_id;
	private String nickname;


	@Builder
	public FriendResDto(Users user) {
		this.uid = user.getUid();
		this.profile_image_id = user.getProfile();
		this.nickname = user.getNickname();
	}


}

