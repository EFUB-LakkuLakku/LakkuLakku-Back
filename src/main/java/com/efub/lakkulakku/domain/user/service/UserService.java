package com.efub.lakkulakku.domain.user.service;

import com.efub.lakkulakku.domain.user.dto.LoginReqDto;
import com.efub.lakkulakku.domain.user.entity.User;
import com.efub.lakkulakku.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Optional<User> findUserByEmail(LoginReqDto loginReqDto) {
        Optional<User> user = userRepository.findByEmail(loginReqDto.getEmail());
        return user;
    }
}
