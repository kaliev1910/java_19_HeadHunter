package com.example.java_19_headhunter.dto;

import lombok.Data;

@Data
public class RespondedApplicantDto {
    private int id;
    private int resumeId;
    private int vacancyId;
    private boolean confirmation;
}
