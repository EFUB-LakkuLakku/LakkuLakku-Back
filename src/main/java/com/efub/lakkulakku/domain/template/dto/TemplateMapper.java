package com.efub.lakkulakku.domain.template.dto;

import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.template.entity.Template;
import com.efub.lakkulakku.domain.template.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TemplateMapper {

	private final TemplateRepository templateRepository;

	public TemplateResDto toTemplateResDto(Template entity) {
		if (entity == null)
			return null;

		return com.efub.lakkulakku.domain.template.dto.TemplateResDto.builder()
				.id(entity.getId())
				.url(entity.getUrl())
				.category(entity.getCategory())
				.build();
	}

	public Template toEntity(Diary diary, TemplateResDto dto) {
		if (diary == null || dto == null)
			return null;

//		templateRepository.deleteById(diary.getTemplate().getId());

		return Template.builder()
				.diary(diary)
				.url(dto.getUrl())
				.category(dto.getCategory())
				.build();
	}

}
