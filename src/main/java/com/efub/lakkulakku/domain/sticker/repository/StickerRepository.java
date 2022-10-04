package com.efub.lakkulakku.domain.sticker.repository;

import com.efub.lakkulakku.domain.sticker.entity.Sticker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StickerRepository extends JpaRepository<Sticker, UUID> {
	Optional<Sticker> findById(UUID id);
}
