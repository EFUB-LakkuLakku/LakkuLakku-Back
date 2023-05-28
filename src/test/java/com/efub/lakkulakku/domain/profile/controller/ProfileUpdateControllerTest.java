package com.efub.lakkulakku.domain.profile.controller;

import com.efub.lakkulakku.domain.profile.entity.Profile;
import com.efub.lakkulakku.domain.profile.service.ProfileService;
import com.efub.lakkulakku.domain.users.controller.LoginController;
import com.efub.lakkulakku.domain.users.dto.ProfileUpdateResDto;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;


@WebMvcTest({ProfileUpdateController.class})//웹 계층 테스트를 위한 어노테이션
@ExtendWith(MockitoExtension.class)
@MockBean(JpaMetamodelMappingContext.class)
class ProfileUpdateControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProfileService profileService;

	@Test
	@TestUsers
	@DisplayName("프로필_업데이트_성공")
	public void testUpdateProfile() throws Exception {

		// given
		Users user = createUsers();
		String nickname = "changeNickname";
		String updateBio = "변경자기소개";

		MockMultipartFile image = new MockMultipartFile(
				"image",
				"image.jpg",
				MediaType.IMAGE_JPEG_VALUE,
				"image data".getBytes()
		);

		ProfileUpdateResDto expectedResponse = new ProfileUpdateResDto(user);

		given(profileService.updateUserProfile(any(), any(), any())).willReturn(expectedResponse);

		// when
		mockMvc.perform(
						put("/api/v1/profile")
								.contentType(MediaType.MULTIPART_FORM_DATA)
								.param("nickname", nickname)
								.param("bio", updateBio)
								.param("image", image.getOriginalFilename(), String.valueOf(image.getBytes()))
								.with(csrf())
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(expectedResponse.getId()))
				.andExpect(jsonPath("$.profileImage").value(expectedResponse.getProfileImage()))
				.andExpect(jsonPath("$.nickname").value(expectedResponse.getNickname()))
				.andExpect(jsonPath("$.bio").value(expectedResponse.getBio()))
				.andDo(print());


		//TODO: json empty 문제

				//.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				//.andExpect(content().json(new ObjectMapper().writeValueAsString(expectedResponse)));
				//.andExpect(jsonPath("$.id"), is(expectedResponse.getId()))
				//.andExpect(jsonPath("$.profileImage").value(expectedResponse.getProfileImage()))
				//.andExpect(jsonPath("$.nickname").value(expectedResponse.getNickname()))
				//.andExpect(jsonPath("$.bio").value(expectedResponse.getBio()));


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