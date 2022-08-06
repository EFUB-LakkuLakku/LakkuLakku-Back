package com.efub.lakkulakku.domain.diary.repository;

import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, UUID> {
	Boolean existsByDate(LocalDate date);

	@Query(value = "SELECT * FROM diary d WHERE d.users_id=:userId AND YEAR(d.date)=:year AND MONTH(d.date)=:month", nativeQuery = true)
	List<Diary> findUsersDiaryByYearAndMonth(@Param("userId") UUID userId, @Param("year") String year, @Param("month") String month);


	boolean existsByDateAndUserId(LocalDate date, UUID userId);

	Optional<Diary> findByDate(LocalDate date);
	Optional<Diary> findByDateAndUserId(LocalDate date, UUID userId);
}
