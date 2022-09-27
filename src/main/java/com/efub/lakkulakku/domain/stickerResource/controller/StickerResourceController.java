package com.efub.lakkulakku.domain.stickerResource.controller;

import com.efub.lakkulakku.domain.stickerResource.service.StickerResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/util")
@RequiredArgsConstructor
public class StickerResourceController {

	private final StickerResourceService stickerResourceService;

//	@GetMapping("/sticker")
//	public ResponseEntity<String> saveDB() {
//		stickerResourceService.saveStickerResource();
//		return ResponseEntity.ok("기본 스티커 저장 성공");
//	}

//	@GetMapping("/sticker")
//	public ResponseEntity<String> saveDB() {
//		stickerResourceService.saveCommentStickerResource();
//		return ResponseEntity.ok("댓글 스티커 저장 성공");
//	}
}
