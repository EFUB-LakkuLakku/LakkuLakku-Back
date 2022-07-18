package com.efub.lakkulakku.domain.user.service;

import com.efub.lakkulakku.domain.user.dto.LoginReqDto;
import com.efub.lakkulakku.domain.user.dto.WithdrawReqDto;
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
    public User findUserByEmail(LoginReqDto loginReqDto) {
        return userRepository.findByEmail(loginReqDto.getEmail())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 유저입니다."));
    }

    @Transactional
    public void deleteUser(WithdrawReqDto withdrawReqDto) {
        User user = userRepository.findByNickname(withdrawReqDto.getNickname())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 유저입니다."));
        userRepository.delete(user);
    }

}
