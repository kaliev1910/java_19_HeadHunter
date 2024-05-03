package com.example.java_19_headhunter.service.interfaces;

import com.example.java_19_headhunter.dto.basicDtos.EducationDto;
import com.example.java_19_headhunter.models.Education;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EducationService {

    void insert(EducationDto educationDto);

    void update(EducationDto educationDto);

    List<EducationDto> findListByResumeId(int resumeId);

    Optional<Education> findEducationById(int educationId);

    void deleteResumesByResumeId(int resumeId);
}
