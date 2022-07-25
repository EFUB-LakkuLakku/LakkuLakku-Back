package com.efub.lakkulakku.global;

import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class BaseTimeEntityTest {

	@Autowired
	UsersRepository userRepository;

	@Test
	@DisplayName("BaseTimeEntity 등록 테스트")
	void createBaseTimeEntity() {
		LocalDateTime now = LocalDateTime.of(2022,7,7,0,0,0);
		userRepository.save(Users.builder()
			.email("efub@gmail.com")
			.password("efub1234!")
			.nickname("이퍼비")
			.build());

		List<Users> usersList = userRepository.findAll();

		Users users = usersList.get(0);

		System.out.println(">>>>> createdDate = " + users.getCreatedOn() + " , modifiedDate = " + users.getModifiedOn());

		assertThat(users.getCreatedOn()).isAfter(now);
		assertThat(users.getModifiedOn()).isAfter(now);
	}
}
