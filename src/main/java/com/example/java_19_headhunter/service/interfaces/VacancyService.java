package com.example.java_19_headhunter.service.interfaces;

import com.example.java_19_headhunter.dto.basicDtos.VacancyDto;
import com.example.java_19_headhunter.dto.createDto.VacancyCreateDto;
import com.example.java_19_headhunter.dto.updateDto.VacancyUpdateDto;
import org.springframework.security.core.Authentication;

import java.util.List;


public interface VacancyService {
    List<VacancyDto> findAll();

    List<VacancyDto> getVacanciesWithPaging(Integer page, Integer pageSize);

    VacancyDto findById(int id);

    List<VacancyDto> findByCategory(int categoryId);

    List<VacancyDto> findByUserId(int applicantId);

    List<VacancyDto> findByUserEmail(String applicantEmail);

    List<VacancyDto> findActiveVacancies();

    List<VacancyDto> findVacanciesBySalaryRange(int salaryFrom, int salaryTo);

    void update(VacancyUpdateDto vacancyDto, Authentication authentication);

    int create(VacancyCreateDto vacancyDto, Authentication authentication);

    void applyForVacancy(int resumeId, int vacancyId);

}
