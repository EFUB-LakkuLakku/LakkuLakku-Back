package com.efub.lakkulakku.domain.friend.controller;

import com.efub.lakkulakku.domain.comment.controller.CommentController;
import com.efub.lakkulakku.domain.comment.repository.CommentRepository;
import com.efub.lakkulakku.domain.comment.service.CommentService;
import com.efub.lakkulakku.domain.diary.service.DiaryService;
import com.efub.lakkulakku.domain.friend.dto.FriendReqDto;
import com.efub.lakkulakku.domain.friend.entity.Friend;
import com.efub.lakkulakku.domain.friend.exception.DuplicateFriendException;
import com.efub.lakkulakku.domain.friend.exception.SelfFriendException;
import com.efub.lakkulakku.domain.friend.service.FriendService;
import com.efub.lakkulakku.domain.profile.entity.Profile;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import com.efub.lakkulakku.domain.users.service.AuthUsers;
import com.efub.lakkulakku.domain.users.service.UsersService;
import com.efub.lakkulakku.global.config.TestUsers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static com.efub.lakkulakku.global.constant.ResponseConstant.DELETE_FRIEND_SUCCESS;
import static com.efub.lakkulakku.global.constant.ResponseConstant.FRIEND_SUCCESS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest({FriendController.class})//웹 계층 테스트를 위한 어노테이션
@ExtendWith(MockitoExtension.class)
@MockBean(JpaMetamodelMappingContext.class) //JPA auditing 기능을 사용하기 때문에 JPA 메타 모델을 처리하기 위해 사용
class FriendControllerTest {
	@Autowired
	private MockMvc mvc;
	@MockBean
	private FriendService friendService;
	@MockBean
	private UsersService usersService;

	@MockBean
	private UsersRepository usersRepository;

	private static final String BASE_URL = "/api/v1/friends";



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



