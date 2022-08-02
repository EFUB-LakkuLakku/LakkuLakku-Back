package com.efub.lakkulakku.domain.stickerResource.repository;

import com.efub.lakkulakku.domain.stickerResource.entity.StickerResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StickerResourceRepository extends JpaRepository<StickerResource, Long> {
	List<StickerResource> findByCategory(String category);
}
