package com.efub.lakkulakku.domain.profile.entity;

import com.efub.lakkulakku.domain.users.entity.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class) //Junit4의 Runwith과 같은 기능을 하는 Junit5 어노테이션
class ProfileTest {

	@Test
	@DisplayName("프로필 생성 테스트")
	void createProfile() {
		Users user = createUsers();
		String bio = "안녕하세요";

		Profile profile = Profile.builder()
				.bio(bio)
				.users(user)
				.file(null)
				.build();

		assertThat(profile.getBio()).isEqualTo(bio);
		assertThat(profile.getFile()).isEqualTo(null);//TODO:지금은 null값이지만 추후에 변경 예정
		assertThat(profile.getUsers()).isEqualTo(user);

	}

	@Test
	@DisplayName("프로필 변경 테스트")
	void updateProfile() {
		Users user = createUsers();
		String bio = "안녕하세요";
		String updateBio = "변경했습니다.";

		Profile profile = Profile.builder()
				.bio(bio)
				.users(user)
				.file(null)
				.build();
		profile.updateBio(updateBio);

		assertThat(profile.getBio()).isEqualTo(updateBio);

	}




	private Users createUsers(){
		final String email = "test@gmail";
		final String nickname = "테스트계정";
		final String encodedPassword = "encodedPassoword";

		Users user = Users.builder()
				.email(email)
				.nickname(nickname)
				.password(encodedPassword)
				.build();
		return user;
	}



}