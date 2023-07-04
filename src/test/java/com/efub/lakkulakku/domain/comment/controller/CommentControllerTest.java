package com.efub.lakkulakku.domain.comment.controller;

import com.efub.lakkulakku.domain.comment.dto.CommentReqDto;
import com.efub.lakkulakku.domain.comment.dto.CommentResDto;
import com.efub.lakkulakku.domain.comment.dto.CommentUpdateResDto;
import com.efub.lakkulakku.domain.comment.entity.Comment;

import com.efub.lakkulakku.domain.comment.repository.CommentRepository;
import com.efub.lakkulakku.domain.comment.service.CommentService;
import com.efub.lakkulakku.domain.diary.entity.Diary;

import com.efub.lakkulakku.domain.diary.service.DiaryService;
import com.efub.lakkulakku.domain.profile.entity.Profile;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.time.LocalDate;
import java.util.UUID;

import static com.efub.lakkulakku.global.constant.ResponseConstant.COMMENT_ADD_SUCCESS;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({CommentController.class})//웹 계층 테스트를 위한 어노테이션
@ExtendWith(MockitoExtension.class)
@MockBean(JpaMetamodelMappingContext.class)
class CommentControllerTest {

	@Autowired
	private MockMvc mvc;
	@MockBean
	private CommentService commentService;

	@MockBean
	private CommentRepository commentRepository;


	@MockBean
	private DiaryService diaryService;


	private static final LocalDate date = LocalDate.of(2022, 7, 7);
	private static final String BASE_URL = "/api/v1/diaries/";

	@Test
	@TestUsers
	@DisplayName("댓글_생성_성공")
	public void createComment() throws Exception {
		Users testUser = createUsers();
		Diary diary = createDiary(testUser);
		String testContent = "test댓글";
		boolean isSecret = true;
		UUID commentId = UUID.randomUUID();
		Comment comment = Comment.builder()
				.content(testContent)
				.diary(diary)
				.commentId(commentId)
				.users(testUser)
				.isSecret(isSecret)
				.build();
		CommentReqDto testCommentReqDto = CommentReqDto.builder()
				.content(testContent)
				.diaryId(diary.getDiaryId())
				.isSecret(isSecret)
				.build();
		CommentResDto testCommentResDto = CommentResDto.builder()
				.id(comment.getCommentId())
				.createdOn(comment.getCreatedOn())
				.parentId(comment.getParentId())
				.content(comment.getContent())
				.nickname(comment.getUsers().getNickname())
				.isSecret(comment.getIsSecret())
				.message(COMMENT_ADD_SUCCESS)
				.build();

		given(diaryService.existsByDate(date)).willReturn(true);
		given(commentService.addComment(any(), any(), any())).willReturn(testCommentResDto);

		// Perform request
		mvc.perform(
						MockMvcRequestBuilders.post(BASE_URL + date + "/comments")
								.contentType(MediaType.APPLICATION_JSON)
								.content(new ObjectMapper().writeValueAsString(testCommentReqDto))
								.with(csrf())
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.content").value(testCommentResDto.getContent()));

		// Verify behavior
		verify(diaryService).existsByDate(date);


	}

	@Test
	@TestUsers
	@DisplayName("댓글_수정_성공")
	public void updateComment() throws Exception {
		Users testUser = createUsers();
		Diary diary = createDiary(testUser);
		String testContent = "test댓글";
		String updateContent = "수정 후 댓글";
		boolean isSecret = true;

		UUID commentId = UUID.randomUUID();
		Comment comment = Comment.builder()
				.content(testContent)
				.diary(diary)
				.commentId(commentId)
				.isSecret(isSecret)
				.users(testUser)
				.build();
		CommentReqDto testCommentReqDto = CommentReqDto.builder()
				.commentId(comment.getCommentId())
				.content(updateContent)
				.diaryId(diary.getDiaryId())
				.isSecret(isSecret)
				.build();
		comment.update(updateContent);
		CommentUpdateResDto testCommentUpdateResDto = CommentUpdateResDto.builder()
				.id(comment.getCommentId())
				.parentId(comment.getParentId())
				.content(comment.getContent())
				.isSecret(comment.getIsSecret())
				.nickname(comment.getUsers().getNickname())
				.modifiedOn(comment.getModifiedOn())
				.build();

		given(commentService.update(any(), any(), any())).willReturn(testCommentUpdateResDto);

		// Perform request
		mvc.perform(
						MockMvcRequestBuilders.put(BASE_URL + date + "/comments")
								.contentType(MediaType.APPLICATION_JSON)
								.content(new ObjectMapper().writeValueAsString(testCommentReqDto))
								.with(csrf())
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.content").value(testCommentUpdateResDto.getContent()));



	}






	private Diary createDiary(Users user){
		UUID diaryId = UUID.randomUUID();
		Diary diary = Diary.builder()
				.user(user)
				.date(date)
				.build();
		diary.setDaryId(diaryId);


		return diary;
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


}