package com.example.java_19_headhunter.service;

import com.example.java_19_headhunter.dto.basicDtos.VacancyDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VacancyService {
    List<VacancyDto> findAll();
    List<VacancyDto> findByCategory(int categoryId);
    List<VacancyDto> findByUserId(int applicantId);

    List<VacancyDto> findByApplicantEmail(String applicantEmail);

    List<VacancyDto> findActiveVacancies();
    List<VacancyDto> findVacanciesBySalaryRange(int salaryFrom, int salaryTo);
    void update(VacancyDto vacancyDto);
    void create(VacancyDto vacancyDto);
    void applyForVacancy(String email, int vacancyId);

}
