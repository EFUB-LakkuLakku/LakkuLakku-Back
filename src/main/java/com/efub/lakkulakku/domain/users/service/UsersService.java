package com.efub.lakkulakku.domain.users.service;

import com.efub.lakkulakku.domain.profile.ProfileRepository;
import com.efub.lakkulakku.domain.profile.entity.Profile;
import com.efub.lakkulakku.domain.users.dto.LoginReqDto;
import com.efub.lakkulakku.domain.users.dto.SignupReqDto;
import com.efub.lakkulakku.domain.users.dto.WithdrawReqDto;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Users signup(SignupReqDto reqDto) {
//        return usersRepository.save(reqDto.toEntity());
        //TODO : 중복체크
        //TODO : 이메일, 비밀번호 형식 체크(필요하다면)
        String encodedPassword = passwordEncoder.encode(reqDto.getPassword());
        reqDto.setPassword(encodedPassword);

        Users user = usersRepository.save(reqDto.toEntity());
        Profile profile = Profile.builder()
                            .file(null)
                            .users(user)
                            .bio("반갑습니다 :)")
                            .build();
        profileRepository.save(profile);
        return user;
    }

    @Transactional
    public Users findUsersByEmail(LoginReqDto loginReqDto) {
        return usersRepository.findByEmail(loginReqDto.getEmail())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 유저입니다."));
    }

    @Transactional
    public void deleteUser(WithdrawReqDto withdrawReqDto) {
        Users users = usersRepository.findByNickname(withdrawReqDto.getNickname())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 유저입니다."));
        usersRepository.delete(users);
    }
}