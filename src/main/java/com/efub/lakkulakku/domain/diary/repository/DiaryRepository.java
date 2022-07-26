package com.efub.lakkulakku.domain.diary.repository;

import com.efub.lakkulakku.domain.diary.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, UUID> {
	Boolean existsByDate(String date);

	Optional<Diary> findByDate(String date);
}
