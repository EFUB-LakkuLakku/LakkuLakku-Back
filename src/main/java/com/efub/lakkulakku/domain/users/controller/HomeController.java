package com.efub.lakkulakku.domain.users.controller;


import com.efub.lakkulakku.domain.users.dto.HomeResDto;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.exception.UserNotFoundException;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import com.efub.lakkulakku.domain.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping({"/api/v1/home/{year}/{month}", "/api/v1/home"})
@RequiredArgsConstructor
public class HomeController {

	private final UsersRepository usersRepository;
	private final UsersService usersService;

	@GetMapping
	public ResponseEntity<HomeResDto> getHomeByDate(@PathVariable(value = "year", required = false) String year, @PathVariable(value = "month", required = false) String month, @RequestParam String nickname) {
		//TODO : LocalDateTime의 범위를 넘어가는 경우 에러 발생
//		if (Integer.parseInt(year) <= 1969 || Integer.parseInt(year) >= 2100)
//			throw new BadDateRequestException();
		Users user = usersRepository.findByNickname(nickname)
				.orElseThrow(() -> new UserNotFoundException());

		return ResponseEntity.ok()
				.body(usersService.getHome(user, year, month));
	}
}
