package com.efub.lakkulakku.domain.users.service;

import com.efub.lakkulakku.domain.profile.ProfileRepository;
import com.efub.lakkulakku.domain.profile.entity.Profile;
import com.efub.lakkulakku.domain.users.dto.SignupReqDto;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SignUpServiceTest {

	@InjectMocks // 실제 객체로 생성되며, @Mock이 붙은 객체를 주입 받습니다.
	UsersService usersService;

	@Mock
	UsersRepository usersRepository;
	@Mock
	ProfileRepository profileRepository;

	@Spy // Mock하지 않은 메소드는 실제 메소드로 동작
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Test
	@DisplayName("회원가입 테스트_성공")
	void signUpUsersService() {
		//given
		String email = "test1234@gmail.com";
		String password = "test1234!!";
		String nickname = "test";
		String bio = "반갑습니다 :)";
		final SignupReqDto signupReqDto = SignupReqDto.builder()
				.email(email)
				.password(password)
				.nickname(nickname)
				.build();
		Users users = signupReqDto.toEntity();
		users.setPassword(encodePassword(signupReqDto.getPassword()));
		UUID id = UUID.randomUUID();
		users.setId(id);

		Profile profile = Profile.builder()
				.file(null)
				.users(users)
				.bio(bio)
				.build();
		//users.setProfile(profile);

		given(usersRepository.save(any(Users.class))).willReturn(users);
		given(usersRepository.findById(id)).willReturn(Optional.of(users));


		// when
		Users users1 = usersService.signup(signupReqDto);

		// then
		Users findUsers = usersRepository.findById(users1.getId()).get();
		assertEquals(users.getEmail(), findUsers.getEmail());
		assertEquals(users.getNickname(), findUsers.getNickname());
		assertEquals(users.getPassword(), findUsers.getPassword());

	}


	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}
}