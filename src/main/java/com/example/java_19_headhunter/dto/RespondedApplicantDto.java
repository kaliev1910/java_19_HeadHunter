package com.example.java_19_headhunter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class RespondedApplicantDto {
    private int id;
    private int resumeId;
    private int vacancyId;
    private boolean confirmation;
}
