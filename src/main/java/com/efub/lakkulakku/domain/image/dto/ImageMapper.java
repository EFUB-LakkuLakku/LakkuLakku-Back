package com.efub.lakkulakku.domain.image.dto;

import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.file.entity.File;
import com.efub.lakkulakku.domain.image.entity.Image;
import com.efub.lakkulakku.domain.image.exception.ImageFileMissingException;
import com.efub.lakkulakku.domain.image.repository.ImageRepository;
import com.efub.lakkulakku.domain.profile.service.ProfileService;
import com.efub.lakkulakku.domain.users.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ImageMapper {

	private final ImageRepository imageRepository;
	private final ProfileService profileService;

	public ImageResDto toImageResDto(Image entity) {

		if (entity == null)
			return null;

		return ImageResDto.builder()
				.id(entity.getImageId())
				.width(entity.getWidth())
				.height(entity.getHeight())
				.x(entity.getX())
				.y(entity.getY())
				.rotation(entity.getRotation())
				.url(entity.getFile().getUrl())
				.build();
	}

	public Image createEntity(ImageReqDto imageReqDto, MultipartFile multipartFile, Users user, Diary diary) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		File file = profileService.uploadImage("image", user.getNickname(), multipartFile);
		return Image.builder()
				.diary(diary)
				.width(width)
				.height(height)
				.x(imageReqDto.getX())
				.y(imageReqDto.getY())
				.rotation(imageReqDto.getRotation())
				.file(file)
				.build();
	}

//	public Image checkIsEntity(ImageReqDto imageReqDto) {
//		if (imageReqDto.getId() == null)
//			return null;
//		return imageRepository.findById(imageReqDto.getId()).orElseThrow(ImageNotFoundException::new);
//	}

	public List<Image> toEntityList(List<ImageReqDto> imageReqDtoList, ImageToEntityDto dto) throws IOException {
		if (dto.getDiary() == null || dto.getMultipartFileList() == null)
			return null;

		if (imageReqDtoList.size() != dto.getMultipartFileList().size())
			throw new ImageFileMissingException();

		List<Image> imageList = new ArrayList<>();
		List<MultipartFile> MultipartFileList = dto.getMultipartFileList();

		for (int i = 0; i < imageReqDtoList.size(); i++) {
			ImageReqDto imageReqDto = imageReqDtoList.get(i);
			MultipartFile multipartFile = MultipartFileList.get(i);
//			TODO: 프론트에서 UUID 값으로 보내줄 때 주석 해제 - 1차 개발 때는 Integer 값으로 보내줌
//			Image image = checkIsEntity(imageReqDto);
//			if (image == null) {
//				image = createEntity(imageReqDto, multipartFile, dto.getUser(), dto.getDiary());
//			}
			Image image = createEntity(imageReqDto, multipartFile, dto.getUser(), dto.getDiary());
			imageList.add(image);
		}
		return imageList;
	}

}