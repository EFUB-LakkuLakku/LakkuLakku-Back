package com.efub.lakkulakku.domain.diary.controller;

import com.efub.lakkulakku.domain.stickerResource.dto.StickerResourceResDto;
import com.efub.lakkulakku.domain.stickerResource.dto.StickerResourceCategoryResDto;
import com.efub.lakkulakku.domain.stickerResource.service.StickerResourceService;
import com.efub.lakkulakku.domain.templateResource.dto.TemplateResourceResDto;
import com.efub.lakkulakku.domain.templateResource.service.TemplateResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/diaries/edit")
@RequiredArgsConstructor
public class DiaryEditController {

	private final TemplateResourceService templateResourceService;
	private final StickerResourceService stickerResourceService;

	@GetMapping("/templates")
	public ResponseEntity<List<TemplateResourceResDto>> getTemplates() {
		List<TemplateResourceResDto> templateList = templateResourceService.getTemplatesByCategory();
		return ResponseEntity.ok().body(templateList);
	}

	@GetMapping(value = {"/stickers", "/stickers/latest"})
	public ResponseEntity<List<StickerResourceResDto>> getStickerByLatest() {
		List<StickerResourceResDto> stickerList = stickerResourceService.getStickersByLatest();
		return ResponseEntity.ok().body(stickerList);
	}

	@GetMapping("/stickers/category")
	public ResponseEntity<List<StickerResourceCategoryResDto>> getStickerByCategory() {
		List<StickerResourceCategoryResDto> stickerList = stickerResourceService.getStickersByCategory();
		return ResponseEntity.ok().body(stickerList);
	}
}
