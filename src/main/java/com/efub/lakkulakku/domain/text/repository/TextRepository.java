package com.efub.lakkulakku.domain.text.repository;

import com.efub.lakkulakku.domain.text.entity.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TextRepository extends JpaRepository<Text, UUID> {
	Optional<Text> findById(UUID id);
	void deleteAllByDiaryId(UUID id);
}
