package com.efub.lakkulakku.domain.stickerResource.service;

import com.efub.lakkulakku.domain.stickerResource.entity.StickerResource;
import com.efub.lakkulakku.domain.stickerResource.repository.StickerResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StickerResourceService {

	private final StickerResourceRepository stickerResourceRepository;

	public final String BASE_URL =
			"https://s3.ap-northeast-2.amazonaws.com/lakku-lakku.com/sticker/";
	public final String[] CATEGORY_NAME = new String[] {"basic", "botanical", "cute", "kitsch"};
	public final String[] COMMON_NAME = new String[] {"/모던", "/bote_st_", "/cute_st_", "/키치"};
	public final Integer[] IMAGE_COUNT = new Integer[] {56, 119, 111, 69};

	public void saveStickerResource() {
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j <= IMAGE_COUNT[i]; j++) {
				String URL = BASE_URL + CATEGORY_NAME + COMMON_NAME + String.valueOf(i) + ".png";

				StickerResource stickerResource = StickerResource.builder()
						.category(CATEGORY_NAME[i]).url(URL).build();
				stickerResourceRepository.save(stickerResource);
			}
		}
	}

}
