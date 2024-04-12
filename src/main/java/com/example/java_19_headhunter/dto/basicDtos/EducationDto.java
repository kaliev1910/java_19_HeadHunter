package com.example.java_19_headhunter.dto.basicDtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class EducationDto {

    private int id;
    private int resumeId;

    @NotBlank(message = "Institution is mandatory")
    @Size(max = 100, message = "Institution should not be greater than 100 characters")
    private String institution;

    @NotBlank(message = "Program is mandatory")
    @Size(max = 100, message = "Program should not be greater than 100 characters")
    private String program;

    @NotBlank(message = "Degree is mandatory")
    @Size(max = 50, message = "Degree should not be greater than 50 characters")
    private String degree;

    private Date startDate;
    private Date endDate;
}