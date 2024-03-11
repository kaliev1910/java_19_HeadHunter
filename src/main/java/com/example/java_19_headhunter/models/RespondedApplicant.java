package com.example.java_19_headhunter.models;

import lombok.Data;

@Data
public class RespondedApplicant {
    private int id;
    private int resumeId;
    private int vacancyId;
    private boolean confirmation;
}
