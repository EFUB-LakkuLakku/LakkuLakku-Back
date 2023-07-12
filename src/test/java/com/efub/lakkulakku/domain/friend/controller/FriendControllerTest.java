package com.efub.lakkulakku.domain.friend.controller;

import com.efub.lakkulakku.domain.friend.dto.FriendReqDto;
import com.efub.lakkulakku.domain.friend.repository.FriendRepository;
import com.efub.lakkulakku.domain.friend.service.FriendService;
import com.efub.lakkulakku.domain.profile.entity.Profile;
import com.efub.lakkulakku.domain.users.dto.SignupReqDto;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import com.efub.lakkulakku.global.config.TestUsers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Objects;

import static com.efub.lakkulakku.global.constant.ResponseConstant.DELETE_FRIEND_SUCCESS;
import static com.efub.lakkulakku.global.constant.ResponseConstant.FRIEND_SUCCESS;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest({FriendController.class})
@ExtendWith({MockitoExtension.class})
@MockBean(JpaMetamodelMappingContext.class)
public class FriendControllerTest {

	/* Service */
	@MockBean
	private FriendService friendService;

	/* Repository */
	@MockBean
	private UsersRepository usersRepository;

	@MockBean
	private FriendRepository friendRepository;

	/* MockMvc */
	@Autowired
	private MockMvc mockMvc;

	private static final String BASE_URL = "/api/v1/friends/";

	// given
	SignupReqDto userSignupReqDto = SignupReqDto.builder()
			.email("user@gmail.com")
			.nickname("테스트유저")
			.password("test1234")
			.build();

	SignupReqDto targetSignupReqDto = SignupReqDto.builder()
			.email("target@gmail.com")
			.nickname("타겟유저")
			.password("test1234")
			.build();

	Users user = createUsers(userSignupReqDto);
	Users target = createUsers(targetSignupReqDto);

	@Test
	@TestUsers
	@DisplayName("성공_친구 추가")
	void addFriend() throws Exception {
		// given
		FriendReqDto friendReqDto = FriendReqDto.builder()
				.uid(target.getUid())
				.build();

		ResponseEntity<String> friendResDto = ResponseEntity.ok(FRIEND_SUCCESS);

		// when
		mockMvc.perform(
				MockMvcRequestBuilders.post(BASE_URL)
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(friendReqDto))
						.with(csrf())
				)
				// then
				.andExpect(status().isOk())
				.andExpect(content().string(Objects.requireNonNull(friendResDto.getBody())))
				.andDo(print());

		// verify
	}

	@Test
	@TestUsers
	@DisplayName("성공_친구 삭제")
	void deleteFriend() throws Exception {
		// given
		FriendReqDto friendReqDto = FriendReqDto.builder()
				.uid(target.getUid())
				.build();

		ResponseEntity<String> friendResDto = ResponseEntity.ok(DELETE_FRIEND_SUCCESS);

		// when
		mockMvc.perform(
				MockMvcRequestBuilders.delete(BASE_URL)
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(friendReqDto))
						.with(csrf())
				)
				.andExpect(status().isOk())
				.andExpect(content().string(Objects.requireNonNull(friendResDto.getBody())))
				.andDo(print());
	}

	private Users createUsers(SignupReqDto signupReqDto){
		final String bio = "안녕하세요";

		Users user = signupReqDto.toEntity();

		Profile profile = Profile.builder()
				.file(null)
				.bio(bio)
				.users(user)
				.build();

		user.setProfile(profile);
		return user;
	}
}
