package com.efub.lakkulakku.domain.diary.controller;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDate;

import static com.efub.lakkulakku.global.constant.ResponseConstant.*;
import static org.hamcrest.Matchers.is;
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

	@BeforeEach()
	public void login() {

	}

	private static final String BASE_URL = "/api/v1/diaries/{date}";
	private static final LocalDate date = LocalDate.of(2022, 7, 7);

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

		@Test
		@DisplayName("실패: 시간대 범위에서 벗어나는 경우")
		public void failure_wrongRange() throws Exception {
			LocalDate failureDate = LocalDate.of(1969, 12, 31);

			mvc.perform(
							get(BASE_URL, failureDate)
					)
					.andExpect(status().isBadRequest())
					.andExpect(jsonPath("$.message", is(BAD_DATE_REQUEST)))
					.andDo(print());
		}

		@Test
		@DisplayName("실패: LocalDate의 형식에 맞지 않는 경우")
		public void failure_wrongFormat() throws Exception {
			LocalDate failureDate = LocalDate.of(2000, 10, 32);

			mvc.perform(
							get(BASE_URL, failureDate)
					)
					.andExpect(status().isBadRequest())
					.andExpect(jsonPath("$.message", is(BAD_DATE_REQUEST)))
					.andDo(print());
		}
	}

	@Nested
	@DisplayName("다이어리 생성 테스트")
	class testCreateDiary {

		@Test
		@DisplayName("성공")
		public void createDiary() throws Exception {
			String message = date + DIARY_CREATE_SUCCESS;

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

			mvc.perform(
							post(BASE_URL, date)
					)
					.andExpect(status().isOk())
					.andDo(print());

			mvc.perform(
							post(BASE_URL, date)
					)
					.andExpect(status().isBadRequest())
					.andExpect(jsonPath("$.message", is(DUPLICATE_DIARY)))
					.andDo(print());
		}
	}

	@Nested
	@DisplayName("다이어리 삭제 테스트")
	class testDeleteDiary {

		@Test
		@DisplayName("성공")
		public void deleteDiary() throws Exception {
			String message = date + DIARY_DELETE_SUCCESS;

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

			mvc.perform(
							post(BASE_URL, date)
					)
					.andExpect(status().isBadRequest())
					.andExpect(jsonPath("$.message", is(NOTFOUND_DIARY)))
					.andDo(print());
		}
	}

	@Nested
	@DisplayName("다이어리 수정 테스트")
	class testUpdateDiary {

		@Test
		@DisplayName("성공")
		public void updateDiary() throws Exception {
			String message = date + DIARY_UPDATE_SUCCESS;
			mvc.perform(
							post(BASE_URL + "/save", date)
					).andExpect(status().isOk())
					.andExpect(jsonPath("$.message", is(message)))
					.andDo(print());
		}
	}
}