package com.example.java_19_headhunter.service.impl;

import com.example.java_19_headhunter.dao.interfaces.EducationDao;
import com.example.java_19_headhunter.dto.EducationDto;
import com.example.java_19_headhunter.models.Education;
import com.example.java_19_headhunter.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EducationServiceImpl implements EducationService {
    private final EducationDao educationDao;

    @Autowired
    public EducationServiceImpl(EducationDao educationDao) {
        this.educationDao = educationDao;
    }

    @Override
    public void insert(EducationDto educationDto) {
        Education education = mapToEducation(educationDto);
        educationDao.insert(education);
    }

    @Override
    public void update(EducationDto educationDto) {
        Education education = mapToEducation(educationDto);
        educationDao.update(education);
    }

    @Override
    public List<EducationDto> findByResumeId(int resumeId) {
        List<Education> educations = educationDao.findByResumeId(resumeId);
        return educations.stream()
                .map(this::mapToEducationDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteByResumeId(int resumeId) {
        educationDao.deleteByResumeId(resumeId);
    }

    private Education mapToEducation(EducationDto educationDto) {
        return Education.builder()
                .id(educationDto.getId())
                .resumeId(educationDto.getResumeId())
                .institution(educationDto.getInstitution())
                .program(educationDto.getProgram())
                .degree(educationDto.getDegree())
                .startDate(educationDto.getStartDate())
                .endDate(educationDto.getEndDate())
                .build();
    }

    private EducationDto mapToEducationDto(Education education) {
        return EducationDto.builder()
                .id(education.getId())
                .resumeId(education.getResumeId())
                .institution(education.getInstitution())
                .program(education.getProgram())
                .degree(education.getDegree())
                .startDate(education.getStartDate())
                .endDate(education.getEndDate())
                .build();
    }
}
