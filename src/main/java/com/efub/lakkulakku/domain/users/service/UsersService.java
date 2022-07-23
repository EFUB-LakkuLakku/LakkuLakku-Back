package com.efub.lakkulakku.domain.users.service;

import com.efub.lakkulakku.domain.users.dto.LoginReqDto;
import com.efub.lakkulakku.domain.users.dto.SignupReqDto;
import com.efub.lakkulakku.domain.users.dto.WithdrawReqDto;
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