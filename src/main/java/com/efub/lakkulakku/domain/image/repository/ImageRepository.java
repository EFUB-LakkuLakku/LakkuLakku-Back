package com.efub.lakkulakku.domain.image.repository;

import com.efub.lakkulakku.domain.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {
	Optional<Image> findById(UUID id);
}
