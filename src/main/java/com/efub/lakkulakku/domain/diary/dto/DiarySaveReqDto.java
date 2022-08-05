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
	DiaryLookupReqDto diaryLookupReqDto;

	@Builder
	public DiarySaveReqDto(Users user, List<MultipartFile> multipartFileList, DiaryLookupReqDto diaryLookupReqDto) {
		this.user = user;
		this.multipartFileList = multipartFileList;
		this.diaryLookupReqDto = diaryLookupReqDto;
	}
}
