package com.efub.lakkulakku.domain.likes.repository;

import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.likes.entity.Likes;
import com.efub.lakkulakku.domain.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LikesRepository extends JpaRepository<Likes, UUID> {

	//void likes(Likes likes);
	//void unlikes(UUID id) throws Exception;
	//Optional<Likes> findLikesByUserandDiaryId(Users user, UUID id);
	Optional<Likes> findLikesById(UUID id);
	//Boolean existsByDate(String date);
	//Optional<Likes> findByDate(String date);
	//void deleteLikesByDiary(Diary diary);
	//void deleteById(UUID id);

}