package com.example.java_19_headhunter.dto.updateDto;

import com.example.java_19_headhunter.dto.basicDtos.ContactInfoDto;
import com.example.java_19_headhunter.dto.basicDtos.EducationDto;
import com.example.java_19_headhunter.dto.basicDtos.ExperienceDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ResumeUpdateDto {
    private int id;
    private String name;
    private String applicantEmail;
    private int expectedSalary;
    private int categoryId;
    private List<EducationDto> education;
    private List<ExperienceDto> experience;
    private List<ContactInfoDto> contactInfo;

}
