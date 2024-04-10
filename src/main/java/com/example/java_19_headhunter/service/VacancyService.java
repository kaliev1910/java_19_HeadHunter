package com.example.java_19_headhunter.service;

import com.example.java_19_headhunter.dto.basicDtos.VacancyDto;
import com.example.java_19_headhunter.dto.createDto.VacancyCreateDto;
import org.springframework.security.core.Authentication;

import java.util.List;


public interface VacancyService {
    List<VacancyDto> findAll();

    List<VacancyDto> findByCategory(int categoryId);

    List<VacancyDto> findByUserId(int applicantId);

    List<VacancyDto> findByUserEmail(String applicantEmail);

    List<VacancyDto> findActiveVacancies();

    List<VacancyDto> findVacanciesBySalaryRange(int salaryFrom, int salaryTo);

    void update(VacancyDto vacancyDto);

    int create(VacancyCreateDto vacancyDto, Authentication authentication);

    void applyForVacancy(String email, int vacancyId);

}
