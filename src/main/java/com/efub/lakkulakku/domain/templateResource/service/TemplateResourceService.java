package com.efub.lakkulakku.domain.templateResource.service;

import com.efub.lakkulakku.domain.templateResource.dto.TemplateResourceResDto;
import com.efub.lakkulakku.domain.templateResource.dto.TemplateResourceMapper;
import com.efub.lakkulakku.domain.templateResource.dto.TemplateResourceDto;
import com.efub.lakkulakku.domain.templateResource.entity.TemplateResource;
import com.efub.lakkulakku.domain.templateResource.repository.TemplateResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TemplateResourceService {

	private final TemplateResourceRepository templateResourceRepository;
	private final TemplateResourceMapper templateResourceMapper;

	public final String BASE_URL =
			"https://s3.ap-northeast-2.amazonaws.com/lakku-lakku.com/template/";
	public final String[] CATEGORY_NAME = new String[]{"basic", "botanical", "cute", "kitsch"};
	public final String[] COMMON_NAME = new String[]{"%E1%84%86%E1%85%A9%E1%84%83%E1%85%A5%E1%86%AB_", "bote_", "cute_", "%E1%84%8F%E1%85%B5%E1%84%8E%E1%85%B5_"};
	public final Integer[] IMAGE_COUNT = new Integer[]{6, 16, 14, 6};

	public void saveTemplateResource() {
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j <= IMAGE_COUNT[i]; j++) {
				String URL = BASE_URL.concat(CATEGORY_NAME[i]).concat("/").concat(COMMON_NAME[i]).concat(Integer.toString(j)).concat(".png");

				TemplateResource templateResource = TemplateResource.builder()
						.category(CATEGORY_NAME[i]).url(URL).build();
				templateResourceRepository.save(templateResource);
			}
		}
	}

	public List<TemplateResourceResDto> getTemplatesByCategory() {
		List<TemplateResourceResDto> templateResourceResDtoList = new ArrayList<>();
		for (String category : CATEGORY_NAME) {
			List<TemplateResourceDto> templateList = templateResourceRepository.findByCategory(category)
					.stream()
					.map(templateResourceMapper::toTemplateResourceDto).collect(Collectors.toList());
			TemplateResourceResDto dto = TemplateResourceResDto.builder()
					.category(category)
					.templateList(templateList)
					.build();
			templateResourceResDtoList.add(dto);
		}
		return templateResourceResDtoList;
	}
}
