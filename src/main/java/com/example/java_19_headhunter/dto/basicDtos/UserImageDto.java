package com.example.java_19_headhunter.dto.basicDtos;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserImageDto {
    private MultipartFile file;
    private long userId;
}
