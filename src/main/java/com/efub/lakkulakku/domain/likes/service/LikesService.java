package com.efub.lakkulakku.domain.likes.service;

//import com.efub.lakkulakku.domain.diary.entity.Diary;

import com.efub.lakkulakku.domain.diary.repository.DiaryRepository;
import com.efub.lakkulakku.domain.likes.dto.LikeResDto;
import com.efub.lakkulakku.domain.likes.entity.Likes;
import com.efub.lakkulakku.domain.likes.exception.DiaryNotFoundException;
import com.efub.lakkulakku.domain.likes.repository.LikesRepository;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.relational.core.sql.Like;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Transactional
@RequiredArgsConstructor
@Service
public class LikesService {
	private final LikesRepository likesRepository;
	private final UsersRepository usersRepository;
	private final DiaryRepository diaryRepository;

	private Users user;

	public void likes(LikeResDto likeResDto/*, String date*/) {

		Likes likes = Likes.builder()
				.id(likeResDto.getId())
				//.diaryId(likeResDto.getDiaryId())
				//.users(usersRepository.findUsersById(likeResDto.getUsersId()).get())
				.build();

		likesRepository.save(likes);

		//updateLikesCount(likeResDto.getId(), 1);

	}

	public void unLikes(/*LikeResDto likeResDto,*/ UUID id/*,String date*/) {

		//Optional<Likes> likesOpt = findById(likeResDto);

		Likes likes = likesRepository.findLikesById(id)
				.orElseThrow(DiaryNotFoundException::new);

		likesRepository.delete(likes);
		//likesRepository.delete(likesOpt.get());
		//likesRepository.deleteById(id);
		//updateLikesCount(likeResDto.getId(), -1);

	}

	private Optional<Likes> findLikesById(LikeResDto likeResDto) {
		return likesRepository
				.findLikesById(likeResDto.getId());

	}

	/*private Optional<Likes> findById(LikeResDto likeResDto) {
		return likesRepository
				.findById(likeResDto.getId());

	}*/

	/*private boolean isNotAlreadyLike(Users user, Diary diary) {
		return likesRepository.findLikesByUserandDiary(user, diary).isEmpty();
	}*/

	/*public void updateLikesCount(UUID diaryId, Integer plusOrMinus) {
        Optional<Diary> diaryOpt = diaryRepository.findById(diaryId);
        if (diaryOpt.isEmpty()) {
            throw new CustomException(Diary_NOT_FOUND);
        }
        UpdateRequest request = new UpdateRequest("diaries", diaryId)
                .doc("like_count", diaryOpt.get().getLikeCount() + plusOrMinus);
	}*/

}