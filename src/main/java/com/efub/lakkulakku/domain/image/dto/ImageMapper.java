package com.efub.lakkulakku.domain.image.dto;

import com.efub.lakkulakku.domain.diary.entity.Diary;
import com.efub.lakkulakku.domain.file.entity.File;
import com.efub.lakkulakku.domain.file.repository.FileRepository;
import com.efub.lakkulakku.domain.image.entity.Image;
import com.efub.lakkulakku.domain.image.exception.ImageFileMissingException;
import com.efub.lakkulakku.domain.image.exception.ImageNotFoundException;
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
	private final FileRepository fileRepository;
	private final ProfileService profileService;

	public ImageResDto toImageResDto(Image entity) {

		if (entity == null)
			return null;

		return ImageResDto.builder()
				.id(entity.getId())
				.width(entity.getWidth())
				.height(entity.getHeight())
				.x(entity.getX())
				.y(entity.getY())
				.url(entity.getFile().getUrl())
				.build();
	}

	public Image createEntity(ImageResDto imageResDto, MultipartFile multipartFile, Users user, Diary diary) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		File file = profileService.uploadImage(user.getNickname(), "Image", multipartFile);
		return Image.builder()
				.diary(diary)
				.width(width)
				.height(height)
				.x(imageResDto.getX())
				.y(imageResDto.getY())
				.file(file)
				.build();
	}

	public Image checkIsEntity(ImageResDto imageResDto) {
		System.out.println("imageResDto ID값=" + imageResDto.getId());
		if (imageResDto.getId() == null)
			return null;
		return imageRepository.findById(imageResDto.getId()).orElseThrow(ImageNotFoundException::new);
	}

	public List<Image> toEntityList(List<ImageResDto> imageResDtoList, ImageToEntityDto dto) throws IOException {
		if (dto.getDiary() == null || dto.getMultipartFileList() == null)
			return null;

		if (imageResDtoList.size() != dto.getMultipartFileList().size())
			throw new ImageFileMissingException();

		List<Image> imageList = new ArrayList<>();
		List<MultipartFile> MultipartFileList = dto.getMultipartFileList();

		for (int i = 0; i < imageResDtoList.size(); i++) {
			ImageResDto imageResDto = imageResDtoList.get(i);
			MultipartFile multipartFile = MultipartFileList.get(i);
			Image image = checkIsEntity(imageResDto);
			if (image == null) {
				image = createEntity(imageResDto, multipartFile, dto.getUser(), dto.getDiary());
			}
			imageList.add(image);
		}
		return imageList;
	}

	public List<Image> deleteAndCreateEntity(List<ImageResDto> imageResDtoList, ImageToEntityDto dto) throws IOException {
		imageRepository.deleteAllByDiaryId(dto.getDiary().getId());
		return toEntityList(imageResDtoList, dto);
	}
}