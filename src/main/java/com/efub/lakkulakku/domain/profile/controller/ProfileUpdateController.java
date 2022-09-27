package com.efub.lakkulakku.domain.profile.controller;

import com.efub.lakkulakku.domain.profile.service.ProfileService;
import com.efub.lakkulakku.domain.users.dto.ProfileUpdateResDto;
import com.efub.lakkulakku.domain.users.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileUpdateController {

	private final ProfileService profileService;

	@PutMapping
	public ProfileUpdateResDto updateProfile(@RequestParam(value = "nickname") String nickname,
											 @RequestParam("image") MultipartFile image,
											 @RequestParam(value = "bio") String bio) throws Exception {
		return profileService.updateUserProfile(nickname, image, bio);

	}
}
