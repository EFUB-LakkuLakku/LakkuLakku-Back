/*package com.efub.lakkulakku.domain.friend.controller;

import com.efub.lakkulakku.domain.friend.dto.FriendReqDto;
import com.efub.lakkulakku.domain.friend.service.FriendService;
import com.efub.lakkulakku.domain.profile.entity.Profile;
import com.efub.lakkulakku.domain.users.entity.AuthUsers;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;


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



	@Test
	//@TestUsers
	@DisplayName("친구추가")
	void addFriend() throws Exception {

		Users user = createUsers();
		Users target = createTarget();
		//FriendReqDto friendReqDto = new FriendReqDto() {};
		FriendReqDto friendReqDto = FriendReqDto.builder()
				.uid(target.getUid())
				.build();
		doNothing().when(friendService).addFriend(eq(friendReqDto), any(Users.class));

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/friends")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(friendReqDto))
						.with(user(new AuthUsers(user)))
						.with(csrf()))
				.andExpect(status().isOk())
				.andDo(print());


	}



	@Test
	@TestUsers
	@DisplayName("친구_생성_자기자신을 친구로 등록")
	public void addFriend_self() throws Exception {
		Long uid = 1L;
		FriendReqDto reqDto = new FriendReqDto(uid);

		assertThrows(SelfFriendException.class, () -> {
			mvc.perform(MockMvcRequestBuilders
					.post(BASE_URL)
					.contentType(MediaType.APPLICATION_JSON)
					.content(new ObjectMapper().writeValueAsString(reqDto))
					.with(csrf()));
		});
	}

	@Test
	@TestUsers
	@DisplayName("친구_생성_중복 친구")
	public void addFriend_duplicate() throws Exception {
		Users user = createUsers();
		Users targetUser = createTarget();

		FriendReqDto reqDto = new FriendReqDto(targetUser.getUid());

		given(usersService.findByUid(any())).willReturn(targetUser);
		given(friendService.isFriend(any(), any())).willReturn(new Friend(user, targetUser).getFriendId());;

		assertThrows(DuplicateFriendException.class, () -> {
			mvc.perform(MockMvcRequestBuilders
					.post(BASE_URL)
					.contentType(MediaType.APPLICATION_JSON)
					.content(new ObjectMapper().writeValueAsString(reqDto))
					.with(csrf()));
		});
	}

	@Test
	@TestUsers
	@DisplayName("친구_생성_성공")
	public void addFriend_success() throws Exception {
		Users user = createUsers();
		Users target = createTarget();
		FriendReqDto reqDto = new FriendReqDto(target.getUid());

		given(usersService.findByUid(any())).willReturn(user);
		given(friendService.isFriend(any(), any())).willReturn(null);

		mvc.perform(MockMvcRequestBuilders
						.post(BASE_URL)
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(reqDto))
						.with(csrf()))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(FRIEND_SUCCESS));
	}

	@Test
	@TestUsers
	@DisplayName("친구_삭제_성공")
	public void deleteFriend() throws Exception{

		Users targetUser = createTarget();
		FriendReqDto reqDto = new FriendReqDto(targetUser.getUid());
		Users user = createUsers();

		doNothing().when(friendService).deleteFriend(any(), any());

		mvc.perform(MockMvcRequestBuilders
						.delete(BASE_URL)
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(reqDto))
						.with(csrf()))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(DELETE_FRIEND_SUCCESS));
	}

	private Users createUsers(){
		final String email = "test@gmail";
		final String nickname = "테스트계정";
		final String encodedPassword = "encodedPassoword";
		final String bio = "안녕";
		final Long uid = 1L;

		Users user = Users.builder()
				.email(email)
				.nickname(nickname)
				.password(encodedPassword)
				.uid(uid)
				.build();
		return user;
	}
	private Users createTarget(){
		final String email = "testFriend@gmail";
		final String nickname = "친구계정";
		final String encodedPassword = "encodedFriendPassoword";
		final String bio = "안녕";
		final Long uid = 2L;

		Users user = Users.builder()
				.email(email)
				.nickname(nickname)
				.password(encodedPassword)
				.uid(uid)
				.build();
		return user;
	}
}
*/
