package com.example.java_19_headhunter.dto.updateDto;

import com.example.java_19_headhunter.dto.basicDtos.ContactInfoDto;
import com.example.java_19_headhunter.dto.basicDtos.EducationDto;
import com.example.java_19_headhunter.dto.basicDtos.ExperienceDto;
import com.example.java_19_headhunter.dto.basicDtos.ResumeDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ResumeUpdateDto {
    private int id;
    private String name;
    private int expectedSalary;
    private int categoryId;
    private List<EducationDto> education;
    private List<ExperienceDto> experience;
    private List<ContactInfoDto> contactInfo;

}
