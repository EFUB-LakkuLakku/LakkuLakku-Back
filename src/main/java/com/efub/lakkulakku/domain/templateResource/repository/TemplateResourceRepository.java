package com.efub.lakkulakku.domain.templateResource.repository;

import com.efub.lakkulakku.domain.templateResource.entity.TemplateResource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemplateResourceRepository extends JpaRepository<TemplateResource, Long> {
	List<TemplateResource> findByCategory(String category);
}
