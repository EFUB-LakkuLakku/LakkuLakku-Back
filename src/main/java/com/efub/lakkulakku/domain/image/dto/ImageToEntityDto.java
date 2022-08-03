package com.efub.lakkulakku.domain.image.dto;

import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.users.entity.Users;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class ImageToEntityDto {
	Diary diary;
	Users user;
	List<MultipartFile> multipartFileList;

	@Builder
	public ImageToEntityDto(Diary diary, Users user, List<MultipartFile> multipartFileList){
		this.diary = diary;
		this.user = user;
		this.multipartFileList = multipartFileList;
	}
}
