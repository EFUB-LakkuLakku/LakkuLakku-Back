package com.efub.lakkulakku.domain.templateResource.controller;

import com.efub.lakkulakku.domain.templateResource.service.TemplateResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/util")
@RequiredArgsConstructor
public class TemplateResourceController {

	private final TemplateResourceService templateResourceService;

//	@GetMapping("/template")
//	public ResponseEntity<String> saveDB() {
//		templateResourceService.saveTemplateResource();
//		return ResponseEntity.ok("기본 템플릿 저장 성공");
//	}
}
