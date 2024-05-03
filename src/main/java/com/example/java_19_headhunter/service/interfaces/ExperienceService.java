package com.example.java_19_headhunter.service.interfaces;

import com.example.java_19_headhunter.dto.basicDtos.ExperienceDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExperienceService {
    void insert(ExperienceDto experienceDto);
//
    void update(ExperienceDto experienceDto);

    List<ExperienceDto> findListByResumeId(int resumeId);

    void deleteEducationsByResumeId(int resumeId);
}
