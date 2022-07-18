package com.efub.lakkulakku.domain.user.service;

import com.efub.lakkulakku.domain.user.dto.SignupReqDto;
import com.efub.lakkulakku.domain.user.entity.User;
import com.efub.lakkulakku.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User signup(SignupReqDto reqDto) {
        return userRepository.save(reqDto.toEntity());
    }

    @Transactional
    public boolean checkEmailDuplicate(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public boolean checkNicknameDuplicate(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

}