package com.example.java_19_headhunter.service.impl;

import com.example.java_19_headhunter.dao.interfaces.ExperienceDao;
import com.example.java_19_headhunter.dto.ExperienceDto;
import com.example.java_19_headhunter.models.Experience;
import com.example.java_19_headhunter.service.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceDao experienceDao;



    @Override
    public void insert(ExperienceDto experienceDto) {
        Experience experience = mapToExperience(experienceDto);
        experienceDao.insert(experience);
    }

    @Override
    public void update(ExperienceDto experienceDto) {
        Experience experience = mapToExperience(experienceDto);
        experienceDao.update(experience);
    }

    @Override
    public List<ExperienceDto> findByResumeId(int resumeId) {
        List<Experience> experiences = experienceDao.findByResumeId(resumeId);
        return experiences.stream()
                .map(this::mapToExperienceDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteByResumeId(int resumeId) {
        experienceDao.deleteByResumeId(resumeId);
    }

    private Experience mapToExperience(ExperienceDto experienceDto) {
        return Experience.builder()
                .id(experienceDto.getId())
                .resumeId(experienceDto.getResumeId())
                .companyName(experienceDto.getCompanyName())
                .position(experienceDto.getPosition())
                .responsibilities(experienceDto.getResponsibilities())
                .years(experienceDto.getYears())
                .build();
    }

    private ExperienceDto mapToExperienceDto(Experience experience) {
        return ExperienceDto.builder()
                .id(experience.getId())
                .resumeId(experience.getResumeId())
                .companyName(experience.getCompanyName())
                .position(experience.getPosition())
                .responsibilities(experience.getResponsibilities())
                .years(experience.getYears())
                .build();
    }

}
