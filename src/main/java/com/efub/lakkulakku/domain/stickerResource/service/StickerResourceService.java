package com.efub.lakkulakku.domain.stickerResource.service;

import com.efub.lakkulakku.domain.stickerResource.dto.StickerResourceMapper;
import com.efub.lakkulakku.domain.stickerResource.dto.StickerResourceResDto;
import com.efub.lakkulakku.domain.stickerResource.dto.StickerResourceCategoryResDto;
import com.efub.lakkulakku.domain.stickerResource.entity.StickerResource;
import com.efub.lakkulakku.domain.stickerResource.repository.StickerResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StickerResourceService {

	private final StickerResourceRepository stickerResourceRepository;
	private final StickerResourceMapper stickerResourceMapper;

	public final String BASE_URL =
			"https://s3.ap-northeast-2.amazonaws.com/lakku-lakku.com/sticker/";
	public final String[] CATEGORY_NAME = new String[]{"basic", "cute", "kitsch", "botanical"};
	public final String[] COMMON_NAME = new String[]{"%E1%84%86%E1%85%A9%E1%84%83%E1%85%A5%E1%86%AB", "cute_st_", "%E1%84%8F%E1%85%B5%E1%84%8E%E1%85%B5", "bote_st_"};
	public final Integer[] IMAGE_COUNT = new Integer[]{56, 111, 69, 119};

	public void saveStickerResource() {
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j <= IMAGE_COUNT[i]; j++) {
				String URL = BASE_URL.concat(CATEGORY_NAME[i]).concat("/").concat(COMMON_NAME[i]).concat(Integer.toString(j)).concat(".png");

				StickerResource stickerResource = StickerResource.builder()
						.category(CATEGORY_NAME[i]).url(URL).build();
				stickerResourceRepository.save(stickerResource);
			}
		}
	}

	public List<StickerResourceResDto> getStickersByLatest() {
		return stickerResourceRepository.findAll().stream().map(stickerResourceMapper::toStickerResourceResDto).collect(Collectors.toList());
	}

	public List<StickerResourceCategoryResDto> getStickersByCategory() {
		List<StickerResourceCategoryResDto> stickerResourceCategoryResDtoList = new ArrayList<>();
		for (String category : CATEGORY_NAME) {
			List<StickerResourceResDto> stickerList = stickerResourceRepository.findByCategory(category)
					.stream()
					.map(stickerResourceMapper::toStickerResourceResDto).collect(Collectors.toList());
			StickerResourceCategoryResDto dto = StickerResourceCategoryResDto.builder()
					.category(category)
					.stickerList(stickerList)
					.build();
			stickerResourceCategoryResDtoList.add(dto);
		}
		return stickerResourceCategoryResDtoList;
	}
}
