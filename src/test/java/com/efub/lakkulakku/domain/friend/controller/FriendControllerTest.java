package com.efub.lakkulakku.domain.friend.controller;

import com.efub.lakkulakku.domain.friend.dto.FriendReqDto;
import com.efub.lakkulakku.domain.friend.service.FriendService;
import com.efub.lakkulakku.domain.profile.entity.Profile;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import com.efub.lakkulakku.global.config.TestUsers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@WebMvcTest({FriendController.class})
@ExtendWith(MockitoExtension.class)
@MockBean(JpaMetamodelMappingContext.class)
class FriendControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	FriendController friendController;

	@MockBean
	private FriendService friendService;

	@MockBean
	private UsersRepository usersRepository;


	private Users createUsers() {
		final String email = "test@gmail";
		final String nickname = "테스트계정";
		final String encodedPassword = "encodedPassword";
		final String bio = "안녕";

		Users user = Users.builder()
				.email(email)
				.nickname(nickname)
				.password(encodedPassword)
				.build();
		Profile profile = Profile.builder()
				.file(null)
				.bio(bio)
				.users(user)
				.build();
		user.setProfile(profile);
		return user;
	}

	@Test
	@TestUsers
	@DisplayName("친구추가")
	void addFriend() throws Exception {

		Users user = createUsers();
		FriendReqDto friendReqDto = new FriendReqDto() {};

		doNothing().when(friendService).addFriend(eq(friendReqDto), any(Users.class));

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/friends")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(friendReqDto))
						.with(csrf()))
				.andExpect(status().isOk())
				.andDo(print());
	}

	@Test
	@TestUsers
	@DisplayName("친구끊기")
	void deleteFriend() throws Exception {

		Users user = createUsers();
		FriendReqDto friendReqDto = new FriendReqDto() {};

		doNothing().when(friendService).deleteFriend(eq(friendReqDto), any(Users.class));

		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/friends")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(friendReqDto))
						.with(csrf()))
				.andExpect(status().isOk())
				.andDo(print());
	}
}

