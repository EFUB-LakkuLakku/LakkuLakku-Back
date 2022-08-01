package com.efub.lakkulakku.domain.likes.service;

import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.diary.repository.DiaryRepository;
import com.efub.lakkulakku.domain.likes.dto.LikeResDto;
import com.efub.lakkulakku.domain.likes.entity.Likes;
import com.efub.lakkulakku.domain.likes.repository.LikesRepository;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//@Transactional
@Service
@RequiredArgsConstructor
public class LikesService {

	private final LikesRepository likesRepository;

	private final UsersRepository userRepository;
	private final DiaryRepository diaryRepository;

	@Transactional
	public void createLike(LikeResDto likeResDto) {
		Users user = userRepository.findByEmail("ziyuu@gmail.com").get();
		Diary diary = diaryRepository.findByDate(LocalDate.parse("2022-08-22")).get();

		Likes likes = Likes.builder()
				.id(likeResDto.getId())
				.users(user)
				.diary(diary)
				.build();

		//Optional<Likes> optional = likesRepository.findById(likes.getId());
		likesRepository.save(likes);
		/*System.out.println("like id : " + likesRepository.findById(likes.getId()).get().getId() +
				"user id : " + likesRepository.findByUsersId(likes.getUsers()).get().getId() +
				"diary id : " + likesRepository.findByDiaryId(likes.getDiary()).get().getId());*/
	}

	public void deleteLikesById(UUID id) {

		Optional<Likes> optional = likesRepository.findById(id);

		if (optional.isPresent()) {
			likesRepository.deleteById(id);
		}

		likesRepository.findAll();
	}

	private Optional<Likes> findById(LikeResDto likeResDto) {
		return likesRepository
				.findById(likeResDto.getId());

	}
}