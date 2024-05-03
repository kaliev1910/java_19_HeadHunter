package com.example.java_19_headhunter.service.impl;

import com.example.java_19_headhunter.dto.basicDtos.EducationDto;
import com.example.java_19_headhunter.models.Education;
import com.example.java_19_headhunter.repository.EducationRepository;
import com.example.java_19_headhunter.service.interfaces.EducationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Slf4j
@Service
@AllArgsConstructor
public class EducationServiceImpl implements EducationService {
    private final EducationRepository educationRepository;


    @Override
    public List<EducationDto> findListByResumeId(int resumeId) {
        List<Education> educations = educationRepository.findEducationsByResumeId_Id(resumeId);
        if (educations.isEmpty()) {
            return Collections.emptyList(); // Возвращаем пустой список, если образование не найдено
        }
        return educations.stream()
                .map(this::mapToEducationDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Education> findEducationById(int educationId) {
        if ( educationRepository.findById(educationId).isEmpty()) {
            log.info("education with id {} not found", educationId);

            return Optional.empty();
        }
        return educationRepository.findById(educationId);
    }

    @Override
    public void deleteEducationsByResumeId(int resumeId) {
        List<Education> educations = educationRepository.findEducationsByResumeId_Id(resumeId);
        if (!educations.isEmpty()) { // Проверяем, есть ли образование для удаления
            educationRepository.deleteAll(educations);
        }
        // Если образование отсутствует, ничего не делаем
    }

    @Override
    public void insert(EducationDto educationDto) {
        Education education = mapToEducation(educationDto);
        educationRepository.save(education);
    }

    @Override
    public void update(EducationDto educationDto) {
        Education education = mapToEducation(educationDto);
        educationRepository.save(education);
    }

    private Education mapToEducation(EducationDto educationDto) {
        return Education.builder()
                .id(educationDto.getId())
                .resumeId(educationRepository.findByResumeId(educationDto.getResumeId()))
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
                .resumeId(education.getResumeId().getId())
                .institution(education.getInstitution())
                .program(education.getProgram())
                .degree(education.getDegree())
                .startDate(education.getStartDate())
                .endDate(education.getEndDate())
                .build();
    }
}
