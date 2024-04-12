package com.example.java_19_headhunter.models;

import com.example.java_19_headhunter.dto.basicDtos.ContactInfoDto;
import com.example.java_19_headhunter.dto.basicDtos.EducationDto;
import com.example.java_19_headhunter.dto.basicDtos.ExperienceDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ResumeList {
    private int id;

    @NotNull
    @Email(message = "Email should be valid")
    private String applicantEmail;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name should not be greater than 100 characters")
    private String name;

    private Integer expectedSalary;
    private int categoryId;
    private boolean isActive;
    private LocalDate createdTime;
    private LocalDate updatedTime;
    private List<EducationDto> educations;
    private List<ExperienceDto> experiences;
    private List<ContactInfoDto> contacts;
}
