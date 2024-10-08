package com.example.java_19_headhunter.api;

import com.example.java_19_headhunter.service.impl.UserImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {
    private final UserImageService userImageService;

    @GetMapping("/download/{imageId}")
    public ResponseEntity<?> downloadImage(@PathVariable long imageId) {
        return userImageService.downloadImage(imageId);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<?> getImageByUserId(@PathVariable Long userId) {
        return userImageService.getImageByUserId(userId);
    }
}
