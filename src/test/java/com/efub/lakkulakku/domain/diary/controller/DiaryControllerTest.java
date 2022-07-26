package com.efub.lakkulakku.domain.diary.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DiaryControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private WebApplicationContext ctx;

	@BeforeEach()
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(ctx)
			.addFilters(new CharacterEncodingFilter("UTF-8", true))
			.alwaysDo(print())
			.build();
	}

	private static final String BASE_URL = "/api/v1/diaries/{date}";
	private static final String date = "22-07-08";

	@Nested
	@DisplayName("특정 날짜 다이어리 조회 테스트")
	class testGetDiaryByDate {

		@Test
		@DisplayName("성공")
		public void success() throws Exception {

			mvc.perform(
					get(BASE_URL, date)
				)
				.andExpect(status().isOk())
				.andDo(print());
		}
	}

	@Nested
	@DisplayName("다이어리 생성 테스트")
	class testCreateDiary {

		@Test
		@DisplayName("성공")
		public void createDiary() throws Exception {
			String message = date + " 날짜의 다이어리가 생성되었습니다.";

			mvc.perform(
					post(BASE_URL, date)
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message", is(message)))
				.andDo(print());
		}

		@Test
		@DisplayName("다이어리 중복 생성 예외")
		public void createDuplicateDiary() throws Exception {
			String message = "해당 날짜에 이미 다이어리가 존재합니다.";

			mvc.perform(
					post(BASE_URL, date)
				)
				.andExpect(status().isOk())
				.andDo(print());

			mvc.perform(
					post(BASE_URL, date)
				)
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message", is(message)))
				.andDo(print());
		}
	}

	@Nested
	@DisplayName("다이어리 삭제 테스트")
	class testDeleteDiary {

		@Test
		@DisplayName("성공")
		public void deleteDiary() throws Exception {
			String message = date + " 날짜의 다이어리가 삭제되었습니다.";

			mvc.perform(
					delete(BASE_URL, date)
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message", is(message)))
				.andDo(print());
		}

		@Test
		@DisplayName("다이어리 미확인 예외")
		public void diaryNotFound() throws Exception {
			String message = "해당 다이어리를 찾을 수 없습니다.";

			mvc.perform(
					post(BASE_URL, date)
				)
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message", is(message)))
				.andDo(print());
		}

	}
}