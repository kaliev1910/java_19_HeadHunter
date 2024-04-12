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
public class ExperienceDto {

    private int id;
    private int resumeId;

    @NotBlank(message = "Company name is mandatory")

    @NotBlank(message = "Company name is mandatory")
    @Size(max = 100, message = "Company name should not be greater than 100 characters")
    private String companyName;

    @NotBlank(message = "Position is mandatory")
    @Size(max = 50, message = "Position should not be greater than 50 characters")
    private String position;

    @NotBlank(message = "Responsibilities is mandatory")
    @Size(max = 500, message = "Responsibilities should not be greater than 500 characters")
    private String responsibilities;

    private int years;
    private Date startDate;
    private Date endDate;
}