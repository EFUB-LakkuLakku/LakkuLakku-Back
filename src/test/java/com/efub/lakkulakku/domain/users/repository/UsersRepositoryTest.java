package com.efub.lakkulakku.domain.users.repository;

import com.efub.lakkulakku.domain.users.entity.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 테스트에서 사용할 데이터베이스를 지정하는 어노테이션, 실제 DB 사용
public class UsersRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UsersRepository usersRepository;

	@Test
	@DisplayName("findByEmail_성공")
	void findByEmail() {
		// given
		String email = "testtest@gmail.com";
		String nickname = "testtest";
		Users user = Users.builder()
				.email(email)
				.nickname(nickname)
				.password("password")
				.build();
		entityManager.persist(user);

		// when
		Optional<Users> result = usersRepository.findByEmail(email);

		// then
		assertTrue(result.isPresent());
		assertEquals(result.get().getNickname(), nickname);
	}

	@Test
	@DisplayName("findByEmail_실패")
	void findByEmailFailure() {
		// given
		String email = "testtttttttt@gmail.com";

		// when
		Optional<Users> result = usersRepository.findByEmail(email);

		// then
		assertFalse(result.isPresent());
	}
}
