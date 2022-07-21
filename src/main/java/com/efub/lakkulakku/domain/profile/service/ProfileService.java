package com.efub.lakkulakku.domain.profile.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.efub.lakkulakku.domain.user.dto.ProfileUpdateResDto;
import com.efub.lakkulakku.domain.user.entity.User;
import com.efub.lakkulakku.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UserRepository userRepository;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;
    private final AmazonS3Client amazonS3Client;

    private static final String FILE_EXTENSION_SEPARATOR = ".";

    private void validateFileExists(MultipartFile multipartFile) throws Exception{
        if (multipartFile.isEmpty()) {
            throw new Exception();
        }
    }

    public static String buildFileName(String category, String originalFileName) {
        int fileExtensionIndex = originalFileName.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        String fileExtension = originalFileName.substring(fileExtensionIndex);
        String fileName = originalFileName.substring(0, fileExtensionIndex);
        String now = String.valueOf(System.currentTimeMillis());

        return category + "/" + fileName + "_" + now + fileExtension;
    }

    public String uploadImage(String category, MultipartFile multipartFile) throws Exception {
        validateFileExists(multipartFile);

        String fileName = buildFileName(category, multipartFile.getOriginalFilename());

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());

        try (InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new Exception();
        }

        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    public void deleteImage(String fileName) {
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
    }

    public void updateImage(String category, MultipartFile multipartFile) throws Exception {
        deleteImage(buildFileName(category, multipartFile.getOriginalFilename()));
        uploadImage(category, multipartFile);
    }

    @Transactional
    public ProfileUpdateResDto updateUserProfile(String nickname, MultipartFile image, String bio) throws Exception {
        System.out.println("유저: " + nickname);
        User entity = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 유저입니다."));

        if (!image.isEmpty())
            updateImage("profile", image);
        if (!bio.isEmpty())
            entity.getProfile().updateBio(bio);

        userRepository.save(entity);
        return new ProfileUpdateResDto(entity);
    }
}
