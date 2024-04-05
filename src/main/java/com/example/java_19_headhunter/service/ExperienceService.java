package com.example.java_19_headhunter.service;

import com.example.java_19_headhunter.dto.ExperienceDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExperienceService {
    void insert(ExperienceDto experienceDto);

    void update(ExperienceDto experienceDto);

    List<ExperienceDto> findByResumeId(int resumeId);

    void deleteByResumeId(int resumeId);
}
