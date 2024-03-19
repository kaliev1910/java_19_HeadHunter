package com.example.java_19_headhunter.service;

import com.example.java_19_headhunter.dto.VacancyDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VacancyService {
    List<VacancyDto> findAll();
    List<VacancyDto> findByCategory(int categoryId);
    List<VacancyDto> findByApplicantId(int applicantId);
    List<VacancyDto> findActiveVacancies();
    List<VacancyDto> findVacanciesBySalaryRange(int salaryFrom, int salaryTo);
    void update(VacancyDto vacancyDto);
    void create(VacancyDto vacancyDto);
}
