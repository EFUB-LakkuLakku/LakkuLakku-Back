package com.efub.lakkulakku.domain.users.dto;

import com.efub.lakkulakku.domain.file.entity.File;
import com.efub.lakkulakku.domain.users.entity.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProfileUpdateResDto {
	private Long id;
	private File profileImage;
	private String nickname;
	private String bio;

	public ProfileUpdateResDto(Users user) {
		this.id = user.getUid();
		this.nickname = user.getNickname();
		this.bio = user.getProfile().getBio();

		if (user.getProfile() == null) {
			this.profileImage = null;
			this.bio = null;
		} else if (user.getProfile().getFile() == null) {
			this.profileImage = null;
		} else {
			this.profileImage = user.getProfile().getFile();
		}
	}
}
