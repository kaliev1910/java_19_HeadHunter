package com.example.java_19_headhunter.service.impl;

import com.example.java_19_headhunter.dao.implementation.UserImageDao;
import com.example.java_19_headhunter.dto.basicDtos.UserImageDto;
import com.example.java_19_headhunter.models.UserImage;
import com.example.java_19_headhunter.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserImageService {
    private static final String SUB_DIR = "images";
    private final FileService fileService;
    private final UserImageDao userImageDao;

    public void uploadImage(UserImageDto userImageDto) {
        String fileName = fileService.saveUploadedFile(userImageDto.getFile(), SUB_DIR);
        UserImage ui = UserImage.builder()
                .userId(userImageDto.getUserId())
                .fileName(fileName)
                .build();
        userImageDao.save(ui);
    }

    public ResponseEntity<?> downloadImage(long imageId) {
        String fileName;
        try {
            UserImage ui = userImageDao.getImageById(imageId);
            fileName = ui.getFileName();
        } catch (NullPointerException e) {
            throw new NoSuchElementException("Image not found");
        }
        return fileService.getOutputFile(fileName, SUB_DIR, MediaType.IMAGE_JPEG);
    }

    public ResponseEntity<?> getImageByUserId(Long userId) {
        var mayBeImage = userImageDao.findImageByUserId(userId);
        if (mayBeImage.isEmpty()) {
            return fileService.getOutputFile("no_image.jpeg", SUB_DIR, MediaType.IMAGE_JPEG);
        }
        return fileService.getOutputFile(mayBeImage.get().getFileName(), SUB_DIR, MediaType.IMAGE_JPEG);
    }
}
