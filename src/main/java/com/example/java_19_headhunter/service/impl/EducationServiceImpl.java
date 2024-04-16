package com.example.java_19_headhunter.service.impl;

import com.example.java_19_headhunter.dao.interfaces.EducationDao;
import com.example.java_19_headhunter.dto.basicDtos.EducationDto;
import com.example.java_19_headhunter.models.Education;
import com.example.java_19_headhunter.service.EducationService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
public class EducationServiceImpl implements EducationService {
    private static final Logger log = LoggerFactory.getLogger(EducationServiceImpl.class);
    private final EducationDao educationDao;

    @Autowired
    public EducationServiceImpl(EducationDao educationDao) {
        this.educationDao = educationDao;
    }

    @Override
    public List<EducationDto> findByResumeId(int resumeId) {
        List<Education> educations = educationDao.findByResumeId(resumeId);
        if (educations.isEmpty()) {
            return Collections.emptyList(); // Возвращаем пустой список, если образование не найдено
        }
        return educations.stream()
                .map(this::mapToEducationDto)
                .collect(Collectors.toList());
    }

    @Override
    public Education findEducationById(int educationId) {
        if ( educationDao.findById(educationId) == null) {
            log.info("education with id {} not found", educationId);
            return null;
        }
        return educationDao.findById(educationId);
    }

    @Override
    public void deleteByResumeId(int resumeId) {
        List<Education> educations = educationDao.findByResumeId(resumeId);
        if (!educations.isEmpty()) { // Проверяем, есть ли образование для удаления
            educationDao.deleteByResumeId(resumeId);
        }
        // Если образование отсутствует, ничего не делаем
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
