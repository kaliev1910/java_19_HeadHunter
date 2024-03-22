package com.example.java_19_headhunter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ResumeDto {
    private int id;
    private String applicantEmail;
    private String name;
    private Integer expectedSalary;
    private int categoryId;
    private boolean isActive;
    private Timestamp createdTime;
    private Timestamp updatedTime;
    private List<ContactInfoDto> contacts;
    private List<EducationDto> educations;
    private List<ExperienceDto> experiences;
}
