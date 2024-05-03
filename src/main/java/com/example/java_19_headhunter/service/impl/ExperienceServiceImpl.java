package com.example.java_19_headhunter.service.impl;

import com.example.java_19_headhunter.dto.basicDtos.ExperienceDto;
import com.example.java_19_headhunter.models.Experience;
import com.example.java_19_headhunter.repository.ExperienceRepository;
import com.example.java_19_headhunter.service.interfaces.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository experienceRepository;

    @Override
    public void insert(ExperienceDto experienceDto) {
        Experience experience = mapToExperience(experienceDto);
        experienceRepository.save(experience);
    }

    @Override
    public void update(ExperienceDto experienceDto) {
        Experience experience = mapToExperience(experienceDto);
        experienceRepository.save(experience);
    }

    @Override
    public List<ExperienceDto> findListByResumeId(int resumeId) {
        List<Experience> experiences = experienceRepository.findListByResumeId(resumeId);
        if (experiences.isEmpty()) {
            return Collections.emptyList();
        }
        return experiences.stream()
                .map(this::mapToExperienceDto)
                .collect(Collectors.toList());
    }

    public void deleteEducationById(int educationId) {
        experienceRepository.deleteById(educationId);
    }

    @Override
    public void deleteEducationsByResumeId(int resumeId) {
        experienceRepository.deleteEducationsByResumeId(resumeId);
    }

    private Experience mapToExperience(ExperienceDto experienceDto) {
        return Experience.builder()
                .id(experienceDto.getId())
                .resumeId(experienceRepository.findById(experienceDto.getId()).get().getResumeId())
                .companyName(experienceDto.getCompanyName())
                .position(experienceDto.getPosition())
                .responsibilities(experienceDto.getResponsibilities())
                .years(experienceDto.getYears())
                .build();
    }

    private ExperienceDto mapToExperienceDto(Experience experience) {
        return ExperienceDto.builder()
                .id(experience.getId())
                .resumeId(experience.getResumeId().getId())
                .companyName(experience.getCompanyName())
                .position(experience.getPosition())
                .responsibilities(experience.getResponsibilities())
                .years(experience.getYears())
                .startDate(experience.getStartDate())
                .endDate(experience.getEndDate())
                .build();
    }
}
