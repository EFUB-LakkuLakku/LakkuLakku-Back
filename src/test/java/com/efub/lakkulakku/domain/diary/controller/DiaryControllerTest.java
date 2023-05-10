package com.efub.lakkulakku.domain.diary.controller;

import com.efub.lakkulakku.domain.diary.dto.DiaryMessageResDto;
import com.efub.lakkulakku.domain.diary.repository.DiaryRepository;
import com.efub.lakkulakku.domain.diary.service.DiaryService;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import com.efub.lakkulakku.global.config.TestUsers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static com.efub.lakkulakku.global.constant.ResponseConstant.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest({DiaryController.class})
@ExtendWith({MockitoExtension.class})
@MockBean(JpaMetamodelMappingContext.class)
public class DiaryControllerTest {

	// DiaryController에 포함된 외부 의존성들은 bean으로 주입해줘야 한다

	/* service */
	@MockBean
	DiaryService diaryService;

	/* repository */
	@MockBean
	UsersRepository userRepository;

	@MockBean
	DiaryRepository diaryRepository;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@TestUsers
	@DisplayName("다이어리 생성")
	void createDiary() throws Exception {
		//given
		LocalDate date = LocalDate.of(2023, 9, 13);
		// 여기에서 ResponseEntity로 감쌌기 때문에 headers, body, statusCodeValue, statusCode가 포함되게 된다
		ResponseEntity<DiaryMessageResDto> diaryMessageResDto = ResponseEntity.ok()
				.body(new DiaryMessageResDto(date + DIARY_CREATE_SUCCESS));

		// when
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/diaries/{date}", date)
				.contentType(MediaType.APPLICATION_JSON)
				.with(csrf()) // 403 Forbidden 방지용
		).andReturn();

		// then
		mvcResult.getResponse()
				.setCharacterEncoding("UTF-8");

		String response = mvcResult.getResponse().getContentAsString();
		assertThat(response)
				.isEqualTo(new ObjectMapper().writeValueAsString(diaryMessageResDto.getBody()));
	}
}