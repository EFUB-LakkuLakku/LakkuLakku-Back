package com.efub.lakkulakku.domain.sticker.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import static com.efub.lakkulakku.global.constant.ResponseConstant.NOTFOUND_STICKER;

public class StickerNotFoundException extends ResourceNotFoundException {
	public StickerNotFoundException() {
		super(NOTFOUND_STICKER);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
