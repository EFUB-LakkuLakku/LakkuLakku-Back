package com.efub.lakkulakku.domain.diary.exception;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class DiaryNotFoundException extends ResourceNotFoundException {
	public DiaryNotFoundException() { super("해당 다이어리를 찾을 수 없습니다"); }
}
