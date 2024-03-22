package com.example.java_19_headhunter.dto;

import com.example.java_19_headhunter.models.RespondedApplicant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class VacancyDto {
    private int id;
    private String  authorEmail;
    private String name;
    private String description;
    private int categoryId;
    private int salary;
    private int expFrom;
    private int expTo;
    private boolean isActive;
    private Timestamp createdDate;
    private Timestamp updateTime;
    private List<RespondedApplicant> respondedApplicantList;
}
