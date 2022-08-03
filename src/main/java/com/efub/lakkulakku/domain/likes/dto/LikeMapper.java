package com.efub.lakkulakku.domain.likes.dto;

import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.likes.entity.Likes;
import com.efub.lakkulakku.domain.likes.exception.LikeNotFoundException;
import com.efub.lakkulakku.domain.likes.repository.LikesRepository;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.exception.UserNotFoundException;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeMapper {

	private final UsersRepository usersRepository;
	private final LikesRepository likesRepository;

	public LikeResDto toLikeResDto(Likes entity) {
		if (entity == null)
			return null;

		return LikeResDto.builder()
				.id(entity.getId())
				.createdOn(entity.getCreatedOn())
				.diaryId(entity.getDiary().getId())
				.isLike(entity.getIsLike())
				.build();
	}

	public Likes checkIsEntity(Diary diary, LikeResDto dto) {
		Likes likes;

		if (dto.getId() == null)
			likes = toEntity(diary, dto);
		else
			likes = likesRepository.findById(dto.getId()).orElseThrow(LikeNotFoundException::new);
		return likes;
	}

	public Likes toEntity(Diary diary, LikeResDto dto) {
		if (diary == null || dto == null)
			return null;

		Users users = usersRepository.findById(dto.getUserId()).orElseThrow(UserNotFoundException::new);

		return Likes.builder()
				.diary(diary)
				.users(users)
				.build();
	}

	public Likes deleteAndCreateEntity(Diary diary, LikeResDto dto) {
		likesRepository.deleteAllByDiaryId(diary.getId());
		return toEntity(diary, dto);
	}
}
