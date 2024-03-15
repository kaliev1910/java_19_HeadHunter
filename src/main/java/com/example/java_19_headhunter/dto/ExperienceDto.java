package com.example.java_19_headhunter.dto;

import lombok.Builder;

@Builder
public class ExperienceDto {
    private int id;
    private int resumeId;
    private String companyName;
    private String position;
    private String responsibilities;
    private int years;

}
