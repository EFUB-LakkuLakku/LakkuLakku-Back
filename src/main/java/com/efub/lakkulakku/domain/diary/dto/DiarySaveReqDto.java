package com.efub.lakkulakku.domain.diary.dto;

import com.efub.lakkulakku.domain.users.entity.Users;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class DiarySaveReqDto {
	Users user;
	List<MultipartFile> multipartFileList;
	DiaryLookupResDto diaryLookupResDto;

	@Builder
	public DiarySaveReqDto(Users user, List<MultipartFile> multipartFileList, DiaryLookupResDto diaryLookupResDto){
		this.user = user;
		this.multipartFileList = multipartFileList;
		this.diaryLookupResDto = diaryLookupResDto;
	}
}
