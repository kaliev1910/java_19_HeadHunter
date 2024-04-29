package com.example.java_19_headhunter.dto.basicDtos;

import lombok.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponseDto {
    private int id;
    private int resumeId;
    private int vacancyId;
    private boolean confirmation;
}
