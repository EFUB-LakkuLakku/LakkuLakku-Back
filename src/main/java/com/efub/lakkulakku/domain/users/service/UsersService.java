package com.efub.lakkulakku.domain.users.service;

import com.efub.lakkulakku.domain.friend.exception.UserNotFoundException;
import com.efub.lakkulakku.domain.profile.ProfileRepository;
import com.efub.lakkulakku.domain.profile.entity.Profile;
import com.efub.lakkulakku.domain.users.dto.WithdrawReqDto;
import com.efub.lakkulakku.domain.users.dto.LoginReqDto;
import com.efub.lakkulakku.domain.users.dto.SignupReqDto;
import com.efub.lakkulakku.domain.users.dto.WithdrawReqDto;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.exception.PasswordNotMatchedException;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import com.efub.lakkulakku.global.config.jwt.JwtProvider;
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
    private final JwtProvider jwtProvider;

    @Transactional
    public Users signup(SignupReqDto reqDto) {
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

    public String login(String email, String password) {
        Users user = usersRepository
                .findByEmail(email).orElseThrow(UserNotFoundException::new);
        checkPassword(password, user.getPassword());
        return jwtProvider.createToken(user.getEmail(), user.getRole());
    }

    private void checkPassword(String password, String encodedPassword) {
        boolean isSame = passwordEncoder.matches(password, encodedPassword);
        if(!isSame) {
            throw new PasswordNotMatchedException();
        }
    }

    @Transactional
    public void deleteUser(WithdrawReqDto withdrawReqDto) {
        Users users = usersRepository.findByNickname(withdrawReqDto.getNickname())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 유저입니다."));
        usersRepository.delete(users);
    }


}