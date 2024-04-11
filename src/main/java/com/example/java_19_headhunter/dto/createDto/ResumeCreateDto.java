package com.example.java_19_headhunter.dto.createDto;

import com.example.java_19_headhunter.dto.basicDtos.ContactInfoDto;
import com.example.java_19_headhunter.dto.basicDtos.EducationDto;
import com.example.java_19_headhunter.dto.basicDtos.ExperienceDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ResumeCreateDto {

    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name should not be greater than 100 characters")
    @NotNull
    private String name;
    private Integer expectedSalary;
    private int categoryId;
    private List<EducationDto> education;
    private List<ExperienceDto> experience;
    private List<ContactInfoDto> contactInfo;

}
