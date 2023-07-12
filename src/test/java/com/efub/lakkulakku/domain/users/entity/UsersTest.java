package com.efub.lakkulakku.domain.users.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//순수한 도메인 로직의 경우 Junit이나 AssetJ 등 테스트 편의 도구외의 다른 프레임 워크의 도움이 필요하지 않는다.
@ExtendWith(SpringExtension.class) //Junit4의 Runwith과 같은 기능을 하는 Junit5 어노테이션
class UsersTest {

	@Test
	@DisplayName("계정 생성 테스트")
	void createUsers() {
		final String email = "test@gmail";
		final String nickname = "테스트계정";
		final String encodedPassword = "encodedPassoword";

		Users user = Users.builder()
				.email(email)
				.nickname(nickname)
				.password(encodedPassword)
				.build();

		assertThat(user.getEmail()).isEqualTo(email);
		assertThat(user.getNickname()).isEqualTo(nickname);
		assertThat(user.getPassword()).isEqualTo(encodedPassword);

	}

}