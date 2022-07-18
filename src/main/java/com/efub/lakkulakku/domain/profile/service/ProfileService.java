package com.efub.lakkulakku.domain.profile.service;

import com.efub.lakkulakku.domain.user.dto.ProfileUpdateReqDto;
import com.efub.lakkulakku.domain.user.dto.ProfileUpdateResDto;
import com.efub.lakkulakku.domain.user.entity.User;
import com.efub.lakkulakku.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UserRepository userRepository;

    @Transactional
    public ProfileUpdateResDto updateUserProfile(ProfileUpdateReqDto req){
        User entity = userRepository.findByNickname(req.getNickname())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 유저입니다."));

        entity.getProfile().updateProfile(req.getImage(), req.getBio());

        userRepository.save(entity);
        return new ProfileUpdateResDto(entity);
    }
}
