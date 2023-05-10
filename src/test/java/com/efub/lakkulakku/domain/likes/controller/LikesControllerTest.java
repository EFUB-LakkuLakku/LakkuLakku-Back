package com.efub.lakkulakku.domain.likes.controller;

import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.diary.repository.DiaryRepository;
import com.efub.lakkulakku.domain.likes.dto.LikeClickResDto;
import com.efub.lakkulakku.domain.likes.dto.LikeReqDto;
import com.efub.lakkulakku.domain.likes.entity.Likes;
import com.efub.lakkulakku.domain.likes.service.LikesService;
import com.efub.lakkulakku.domain.profile.controller.ProfileUpdateController;
import com.efub.lakkulakku.domain.profile.entity.Profile;
import com.efub.lakkulakku.domain.profile.service.ProfileService;
import com.efub.lakkulakku.domain.users.entity.Users;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.efub.lakkulakku.global.constant.ResponseConstant.LIKES_ADD_SUCCESS;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest({LikesController.class})//웹 계층 테스트를 위한 어노테이션
@ExtendWith(MockitoExtension.class)
@MockBean(JpaMetamodelMappingContext.class)
class LikesControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LikesService likesService;

	@MockBean
	private DiaryRepository diaryRepository;

	@Test
	@TestUsers
	@DisplayName("좋아요_생성_성공")
	public void createdLikes() throws Exception {
		final boolean isLike = true;
		Users user = createUsers();
		Diary diary = createDiary(user);
		LocalDate date = LocalDate.of(2023, 5, 9);
		LikeReqDto likeReqDto = new LikeReqDto(diary.getId());
		Likes likes = Likes.builder()
				.diary(diary)
				.users(user)
				.isLike(isLike)
				.build();
		LikeClickResDto likeClickResDto = LikeClickResDto.builder()
				.id(likes.getId())
				.diaryId(diary.getId())
				.message(LIKES_ADD_SUCCESS)
				.isLike(isLike)
				.build();

		// Mock the dependencies
		given(likesService.clickLike(any(), any(), any())).willReturn(likeClickResDto);

		// Perform the request and check the results
		mockMvc.perform(
						MockMvcRequestBuilders.post("/api/v1/diaries/{date}/like", date)
								.contentType(MediaType.APPLICATION_JSON)
								.content(new ObjectMapper().writeValueAsString(likeReqDto))
								.with(csrf())
				)

				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.isLike").value(likeClickResDto.getIsLike()));

	}


	private Users createUsers(){
		final String email = "test@gmail";
		final String nickname = "테스트계정";
		final String encodedPassword = "encodedPassoword";
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

	private Diary createDiary(Users user){
		LocalDate date = LocalDate.of(2023, 5, 9);

		Diary diary = Diary.builder()
				.user(user)
				.date(date)
				.build();


		return diary;
	}

}