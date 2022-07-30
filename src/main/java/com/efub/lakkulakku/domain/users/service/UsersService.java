package com.efub.lakkulakku.domain.users.service;

import com.efub.lakkulakku.domain.profile.ProfileRepository;
import com.efub.lakkulakku.domain.profile.entity.Profile;
import com.efub.lakkulakku.domain.users.dto.WithdrawReqDto;
import com.efub.lakkulakku.domain.users.dto.*;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final ProfileRepository profileRepository;
    private final HomeMapper homeMapper;

    @Transactional
    public Users signup(SignupReqDto reqDto) {
//        return usersRepository.save(reqDto.toEntity());
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

    public HomeResDto getHome(Users user, String year, String month) {
        if (year == null) {
            Date date = new Date();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int nowYear = localDate.getYear();
            int nowMonth = localDate.getMonthValue();

            return homeMapper.toHomeResDto(user, Integer.toString(nowYear), Integer.toString(nowMonth));
        }
        else {
            return homeMapper.toHomeResDto(user, year, month);
        }
    }
}