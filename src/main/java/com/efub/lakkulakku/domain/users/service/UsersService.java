package com.efub.lakkulakku.domain.users.service;

import com.efub.lakkulakku.domain.users.dto.SignupReqDto;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

    @Transactional
    public Users signup(SignupReqDto reqDto) {
        return usersRepository.save(reqDto.toEntity());
    }

    @Transactional
    public boolean checkEmailDuplicate(String email) {
        return usersRepository.existsByEmail(email);
    }

    @Transactional
    public boolean checkNicknameDuplicate(String nickname) {
        return usersRepository.existsByNickname(nickname);
    }

}