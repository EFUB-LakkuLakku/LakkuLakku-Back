package com.efub.lakkulakku.domain.profile.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.efub.lakkulakku.domain.file.entity.File;
import com.efub.lakkulakku.domain.file.exception.FileExtenstionException;
import com.efub.lakkulakku.domain.file.exception.S3IOException;
import com.efub.lakkulakku.domain.file.repository.FileRepository;
import com.efub.lakkulakku.domain.profile.ProfileRepository;
import com.efub.lakkulakku.domain.users.dto.ProfileUpdateResDto;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.exception.UserNotFoundException;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UsersRepository userRepository;
    private final ProfileRepository profileRepository;
    private final FileRepository fileRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;
    private final AmazonS3Client amazonS3Client;

    private static final String FILE_EXTENSION_SEPARATOR = ".";

    public static ArrayList<String> buildFileName(String category, String nickname, String originalFileName) {
        int fileExtensionIndex = originalFileName.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        String fileExtension = originalFileName.substring(fileExtensionIndex);
        String fileName = originalFileName.substring(0, fileExtensionIndex);

        String fullFileName = category + "/" + fileName + "_" + nickname + fileExtension;
        ArrayList<String> array = new ArrayList<String>(Arrays.asList(fileName, fileExtension, fullFileName));

        return array;
    }

    public File uploadImage(String category, String nickname, MultipartFile multipartFile) throws S3IOException, FileExtenstionException {
        ArrayList<String> fileInfo = buildFileName(category, nickname, multipartFile.getOriginalFilename());
        String fullFileName = fileInfo.get(2);
        String fileExtension = fileInfo.get(1);

        if (!(fileExtension.equals(".png") || fileExtension.equals(".jpg") || fileExtension.equals("jpeg")))
            throw new FileExtenstionException();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());

        try (InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3Client.putObject(new PutObjectRequest(bucket, fullFileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new S3IOException();
        }

        String url = amazonS3Client.getUrl(bucket, fullFileName).toString();

        File file = File.builder()
                .filename(fileInfo.get(0))
                .filetype(fileInfo.get(1))
                .url(url)
                .build();
        fileRepository.save(file);

        return file;
    }

    public void deleteProfileImage(Users user) {
        // TODO: s3 ?????? ?????? ?????? ?????? ??????
//        fileRepository.delete(user.getProfile().getFile());

        String fileExtension = user.getProfile().getFile().getFiletype();
        String fullFileName = "profile/*_" + user.getNickname() + fileExtension;

        amazonS3.deleteObject(new DeleteObjectRequest(bucket, fullFileName));
    }

    public void updateImage(String category, Users user, MultipartFile multipartFile) throws FileExtenstionException {
        if (user.getProfile().getFile() != null) {
            user.getProfile().getFile();
            deleteProfileImage(user);
        } else {
            System.out.println("?????? ???????????? ???????????? ???????????????.");
        }
        File newFile = uploadImage(category, user.getNickname(), multipartFile);

        user.getProfile().updateFile(newFile);
        profileRepository.save(user.getProfile());
    }

    public ProfileUpdateResDto updateUserProfile(String nickname, MultipartFile image, String bio) throws FileExtenstionException {
        Users entity = userRepository.findByNickname(nickname)
                .orElseThrow(UserNotFoundException::new);

        if (!image.isEmpty())
            updateImage("profile", entity, image);
        if (!bio.isEmpty())
            entity.getProfile().updateBio(bio);

        return new ProfileUpdateResDto(entity);
    }
}
