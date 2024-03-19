package com.example.java_19_headhunter.service.impl;

import com.example.java_19_headhunter.dao.interfaces.VacancyDao;
import com.example.java_19_headhunter.dto.VacancyDto;
import com.example.java_19_headhunter.models.Vacancy;
import com.example.java_19_headhunter.service.VacancyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class  VacancyServiceImpl implements VacancyService  {
    @Autowired
    private VacancyDao vacancyDao;

    private VacancyDto toDto(Vacancy vacancy) {
        return VacancyDto.builder()
                .id(vacancy.getId())
                .authorId(vacancy.getAuthorId())
                .name(vacancy.getName())
                .description(vacancy.getDescription())
                .categoryId(vacancy.getCategoryId())
                .salary(vacancy.getSalary())
                .expFrom(vacancy.getExpFrom())
                .expTo(vacancy.getExpTo())
                .isActive(vacancy.isActive())
                .createdDate(vacancy.getCreatedDate())
                .updateTime(vacancy.getUpdateTime())
                .build();
    }

    private Vacancy fromDto(VacancyDto vacancyDto) {
        return Vacancy.builder()
                .id(vacancyDto.getId())
                .authorId(vacancyDto.getAuthorId())
                .name(vacancyDto.getName())
                .description(vacancyDto.getDescription())
                .categoryId(vacancyDto.getCategoryId())
                .salary(vacancyDto.getSalary())
                .expFrom(vacancyDto.getExpFrom())
                .expTo(vacancyDto.getExpTo())
                .isActive(vacancyDto.isActive())
                .createdDate(vacancyDto.getCreatedDate())
                .updateTime(vacancyDto.getUpdateTime())
                .build();
    }

    @Override
    public List<VacancyDto> findAll() {
        try {
            return vacancyDao.findAll().stream().map(this::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error finding all vacancies", e);
            throw e; // rethrow the exception
        }
    }

    @Override
    public List<VacancyDto> findByCategory(int categoryId) {
        try {
            return vacancyDao.findByCategory(categoryId).stream().map(this::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error finding vacancies by category {}", categoryId, e);
            throw e; // rethrow the exception
        }
    }

    @Override
    public List<VacancyDto> findByApplicantId(int applicantId) {
        try {
            return vacancyDao.findByApplicantId(applicantId).stream().map(this::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error finding vacancies by applicantId {}", applicantId, e);
            throw e; // rethrow the exception
        }
    }

    @Override
    public List<VacancyDto> findActiveVacancies() {
        try {
            return vacancyDao.findActiveVacancies().stream().map(this::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error finding active vacancies", e);
            throw e; // rethrow the exception
        }
    }

    @Override
    public List<VacancyDto> findVacanciesBySalaryRange(int salaryFrom, int salaryTo) {
        try {
            return vacancyDao.findVacanciesBySalaryRange(salaryFrom, salaryTo).stream().map(this::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error finding vacancies by salary range {} - {}", salaryFrom, salaryTo, e);
            throw e; // rethrow the exception
        }
    }

    @Override
    public void update(VacancyDto vacancyDto) {
        try {
            vacancyDao.updateVacancy(fromDto(vacancyDto));
        } catch (Exception e) {
            log.error("Error updating vacancy {}", vacancyDto, e);
            throw e; // rethrow the exception
        }
    }

    @Override
    public void create(VacancyDto vacancyDto) {
        try {
            vacancyDao.createVacancy(fromDto(vacancyDto));
        } catch (Exception e) {
            log.error("Error creating vacancy {}", vacancyDto, e);
            throw e; // rethrow the exception
        }
    }

}
