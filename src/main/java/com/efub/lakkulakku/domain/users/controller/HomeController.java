package com.efub.lakkulakku.domain.users.controller;

import com.efub.lakkulakku.domain.users.dto.HomeResDto;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import com.efub.lakkulakku.domain.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/v1/home/{year}/{month}", "/api/v1/home"})
@RequiredArgsConstructor
public class HomeController {

	private final UsersRepository usersRepository;
	private final UsersService usersService;

	@GetMapping
	public ResponseEntity<HomeResDto> getHomeByDate(@PathVariable(value = "year", required = false) String year, @PathVariable(value = "month", required = false) String month) {
		//TODO : LocalDateTime의 범위를 넘어가는 경우 에러 발생
		//TODO : 시큐리티 적용 후 유저 변경
		Users user = usersRepository.findByNickname("ywyw").get(); // 테스트용 유저

		return ResponseEntity.ok()
				.body(usersService.getHome(user, year, month));
	}
}
