package com.efub.lakkulakku.domain.users.service;

import com.efub.lakkulakku.domain.users.entity.AuthUsers;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.exception.BadTokenRequestException;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUsersDetailsService implements UserDetailsService {

	private final UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users users = usersRepository.findByEmail(email)
				.orElseThrow(BadTokenRequestException::new);
		return new AuthUsers(users);
	}
}
