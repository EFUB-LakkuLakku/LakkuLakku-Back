package com.efub.lakkulakku.domain.comment.controller;

import com.efub.lakkulakku.domain.comment.dto.CommentDeleteReqDto;
import com.efub.lakkulakku.domain.comment.dto.CommentReqDto;
import com.efub.lakkulakku.domain.comment.dto.CommentResDto;
import com.efub.lakkulakku.domain.comment.dto.CommentUpdateResDto;
import com.efub.lakkulakku.domain.comment.entity.Comment;
import com.efub.lakkulakku.domain.comment.exception.ParentNotFoundException;
import com.efub.lakkulakku.domain.comment.repository.CommentRepository;
import com.efub.lakkulakku.domain.comment.service.CommentService;
import com.efub.lakkulakku.domain.diary.controller.DiaryController;
import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.diary.exception.DiaryNotFoundException;
import com.efub.lakkulakku.domain.diary.repository.DiaryRepository;
import com.efub.lakkulakku.domain.diary.service.DiaryService;
import com.efub.lakkulakku.domain.profile.entity.Profile;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.global.config.TestUsers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static com.efub.lakkulakku.global.constant.ResponseConstant.COMMENT_ADD_SUCCESS;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest({CommentController.class})//웹 계층 테스트를 위한 어노테이션
@ExtendWith(MockitoExtension.class)
@MockBean(JpaMetamodelMappingContext.class)
class CommentControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	CommentController commentController;

	@MockBean
	private CommentService commentService;

	@MockBean
	private CommentRepository commentRepository;

	@MockBean
	private DiaryRepository diaryRepository;

	@MockBean
	private DiaryService diaryService;


	private Users createUsers(){
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

	private Diary createDiary(Users user){
		LocalDate date = LocalDate.of(2023, 4, 27);

		Diary diary = Diary.builder()
				.user(user)
				.date(date)
				.build();

		return diary;

	}

	@Test
	@TestUsers
	@DisplayName("댓글 작성")
	public void testCommentAdd() throws Exception {

		Users user = createUsers();
		Diary diary = createDiary(user);
		String content = "test content";
		boolean isSecret = false;

		LocalDate date = LocalDate.of(2023, 4, 27);

		Comment comment = Comment.builder()
				.diary(diary)
				.users(user)
				.content(content)
				.build();

		CommentReqDto commentReqDto = new CommentReqDto(user.getId(), diary.getId(), content, isSecret, comment.getParentId());

		CommentResDto commentResDto = CommentResDto.builder()
				.userId(comment.getUsers().getId())
				.content(comment.getContent())
				.createdOn(comment.getCreatedOn())
				.message(COMMENT_ADD_SUCCESS)
				.build();


		given(commentService.addComment(any(), any(), any())).willReturn(commentResDto);

		mockMvc.perform(
						MockMvcRequestBuilders.post("/api/v1/diaries/{date}/comments", date)
								.contentType(MediaType.APPLICATION_JSON)
								.content(new ObjectMapper().writeValueAsString(commentReqDto))
								.with(csrf())
				)

				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.content").value(commentResDto.getContent()));

	}

	/*@Test
	@DisplayName("댓글 조회")
	@TestUsers
	void testGetComment() throws Exception {

		Users user = createUsers();
		Diary diary = createDiary(user);
		String content = "test content";
		boolean isSecret = false;
		LocalDate date = LocalDate.of(2023, 4, 27);
		CommentReqDto commentReqDto = new CommentReqDto();
		Comment comment = Comment.builder()
				.diary(diary)
				.users(user)
				.content(content)
				.build();
		CommentResDto commentResDto = new CommentResDto();
		List<CommentResDto> comments = diaryService.getDiaryComments(diary);

		given(diaryService.getDiaryComments(any())).willReturn(Collections.singletonList(commentResDto));

		mockMvc.perform(
						MockMvcRequestBuilders.get("/api/v1/diaries/{date}/comments", date)
								.contentType(MediaType.APPLICATION_JSON)
								.content(new ObjectMapper().writeValueAsString(commentReqDto))
								.with(csrf())
				)

				.andExpect(status().isOk())
				.andDo(print());

	}*/


}