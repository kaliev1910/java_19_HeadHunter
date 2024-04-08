package com.example.java_19_headhunter.service;

import com.example.java_19_headhunter.dto.basicDtos.EducationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EducationService {

    void insert(EducationDto educationDto);

    void update(EducationDto educationDto);

    List<EducationDto> findByResumeId(int resumeId);

    void deleteByResumeId(int resumeId);
}
