package com.efub.lakkulakku.domain.user.service;

import com.efub.lakkulakku.domain.user.dto.SettingsInfoDto;
import com.efub.lakkulakku.domain.user.dto.SettingsUpdateDto;
import com.efub.lakkulakku.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/*import org.springframework.security.crypto.password.PasswordEncoder;*/

@Service
@RequiredArgsConstructor
@Transactional
public class SettingsServiceImpl implements SettingsService{

    private final UserRepository userRepository;
    //private final PasswordEncoder passwordEncoder;

    @Override
    public void update(SettingsUpdateDto settingsUpdateDto, String username) throws Exception {
        //User user = userRepository.findByEmail(SecurityUtil.getLoginUsername()).orElseThrow(() -> new Exception("회원이 존재하지 않습니다"));
        //settingsUpdateDto.email().ifPresent(user::updateEmail);
    }

    @Override
    public void updatePassword(String beforePassword, String afterPassword, String email) throws Exception {
        //User user = userRepository.findByEmail(SecurityUtil.getLoginUsername()).orElseThrow(() -> new Exception("회원이 존재하지 않습니다"));

        /*if(!user.matchPassword(passwordEncoder, beforePassword) ) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }
        user.updatePassword(passwordEncoder, afterPassword);*/
    }



}
