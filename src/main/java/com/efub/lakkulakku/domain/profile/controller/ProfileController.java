package com.efub.lakkulakku.domain.profile.controller;

import com.efub.lakkulakku.domain.profile.service.ProfileService;
import com.efub.lakkulakku.domain.user.dto.ProfileUpdateReqDto;
import com.efub.lakkulakku.domain.user.dto.ProfileUpdateResDto;
import com.efub.lakkulakku.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PutMapping
    public ProfileUpdateResDto updateProfile(@Valid @RequestBody ProfileUpdateReqDto profileUpdateReqDto){
        return profileService.updateUserProfile(profileUpdateReqDto);
    }
}
