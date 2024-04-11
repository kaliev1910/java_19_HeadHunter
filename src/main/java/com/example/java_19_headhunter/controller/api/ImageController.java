package com.example.java_19_headhunter.controller.api;

import com.example.java_19_headhunter.dto.basicDtos.UserImageDto;
import com.example.java_19_headhunter.service.impl.UserImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/images")@RequiredArgsConstructor
public class ImageController {
    private final UserImageService userImageService;

    @GetMapping("/download/{imageId}")
    public ResponseEntity<?> downloadImage(@PathVariable long imageId) {
        return userImageService.downloadImage(imageId);
    }

    @PostMapping("/upload")
    public HttpStatus uploadImage(UserImageDto userImageDto) {
        userImageService.uploadImage(userImageDto);
        return HttpStatus.OK;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getImageByMovie(@PathVariable Long userId) {
        return userImageService.getImageByUserId(userId);
    }
}
