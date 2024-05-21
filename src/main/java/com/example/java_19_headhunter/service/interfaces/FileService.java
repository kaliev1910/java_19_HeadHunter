package com.example.java_19_headhunter.service.interfaces;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    ResponseEntity<?> getOutputFile(String fileName, String subDir, MediaType mediaType);
    String saveUploadedFile(MultipartFile file, String subDir);
}
