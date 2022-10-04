package com.efub.lakkulakku.domain.users.service;

import com.efub.lakkulakku.domain.users.dto.PasswordUpdateDto;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.exception.BeforePasswordNotMatchException;
import com.efub.lakkulakku.domain.users.exception.PasswordsNotEqualException;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SettingsServiceImpl implements SettingsService {

	private final UsersRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	public void updatePassword(Users user, PasswordUpdateDto updatePwDto) {
		String beforePw = updatePwDto.getBeforePassword();
		String checkAfterPw = updatePwDto.getCheckAfterPassword();
		String afterPw = updatePwDto.getAfterPassword();
		if (passwordEncoder.matches(beforePw, user.getPassword())) {
			if (checkAfterPw.equals(afterPw)) {
				user.setPassword(passwordEncoder.encode(afterPw));
				userRepository.save(user);
			} else {
				throw new PasswordsNotEqualException();
			}
		} else {
			throw new BeforePasswordNotMatchException();
		}
	}


}
