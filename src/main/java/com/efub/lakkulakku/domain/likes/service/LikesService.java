package com.efub.lakkulakku.domain.likes.service;


//import com.efub.lakkulakku.domain.diary.entity.Diary;

import com.efub.lakkulakku.domain.likes.dto.LikeResDto;
import com.efub.lakkulakku.domain.likes.entity.Likes;
import com.efub.lakkulakku.domain.likes.repository.LikesRepository;
import com.efub.lakkulakku.domain.users.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.data.relational.core.sql.Like;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class LikesService {
	private final LikesRepository likesRepository;
	//private final UsersRepository usersRepository;
	//private final DiaryRepository diaryRepository;

	private Users users;

	public void likes(LikeResDto likeResDto) {

		Likes likes = Likes.builder()
				.id(likeResDto.getId())
				//.users(usersRepository.findUserById(likeResDto.getUserId()).get())
				.build();

		likesRepository.save(likes);

		//updateLikesCount(likeResDto.getUserId(), 1);

	}


	public void unLikes(LikeResDto likeResDto) {

		Optional<Likes> likesOpt = findLikesByUserAndDiaryId(likeResDto);

		likesRepository.delete(likesOpt.get());

		//updateLikesCount(likeResDto.getUserId(), -1);

	}

	private Optional<Likes> findLikesByUserAndDiaryId(LikeResDto likeResDto) {
		return likesRepository
				.findLikesByUserAndDiaryId(users, likeResDto.getId());

	}


	/*public void updateLikesCount(UUID diaryId, Integer plusOrMinus) {

        Optional<Diary> diaryOpt = diaryRepository.findById(diaryId);
        if (diaryOpt.isEmpty()) {
            throw new CustomException(Diary_NOT_FOUND);
        }

        UpdateRequest request = new UpdateRequest("diaries", diaryId)
                .doc("like_count", diaryOpt.get().getLikeCount() + plusOrMinus);


	}*/

}
