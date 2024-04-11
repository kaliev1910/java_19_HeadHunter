package com.example.java_19_headhunter.service.impl;

import com.example.java_19_headhunter.dao.interfaces.UserDao;
import com.example.java_19_headhunter.dao.interfaces.VacancyDao;
import com.example.java_19_headhunter.dto.basicDtos.VacancyDto;
import com.example.java_19_headhunter.dto.createDto.VacancyCreateDto;
import com.example.java_19_headhunter.dto.updateDto.VacancyUpdateDto;
import com.example.java_19_headhunter.models.User;
import com.example.java_19_headhunter.models.Vacancy;
import com.example.java_19_headhunter.service.VacancyService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j

public class VacancyServiceImpl implements VacancyService {
    private final VacancyDao vacancyDao;
    private final UserDao userDao;

    public VacancyServiceImpl(VacancyDao vacancyDao, UserDao userDao) {
        this.vacancyDao = vacancyDao;
        this.userDao = userDao;
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
    public VacancyDto findById(int id) {
        return toDto(vacancyDao.findById(id));
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
    public List<VacancyDto> findByUserId(int userId) {
        try {
            return vacancyDao.findByUserId(userId).stream().map(this::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error finding vacancies by userId {}", userId, e);
            throw e; // rethrow the exception
        }
    }

    @Override
    public List<VacancyDto> findByUserEmail(String applicantEmail) {
        try {
            return vacancyDao.findByApplicantEmail(applicantEmail).stream().map(this::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error finding vacancies by applicantEmail {}", applicantEmail, e);
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
    public void update(VacancyUpdateDto vacancyDto, Authentication a) {
        try {
            vacancyDto.setUpdatedDate(LocalDate.now());
            vacancyDao.updateVacancy(fromUpdateDto(vacancyDto, a));
        } catch (Exception e) {
            log.error("Error updating vacancy {}", vacancyDto, e);
            throw e; // rethrow the exception
        }
    }

    @Override
    public int create(VacancyCreateDto vacancyDto, Authentication authentication) {
        int vacancyId;
        try {
            Vacancy vacancy = fromCreateDto(vacancyDto, authentication);
            vacancy.setAuthorEmail(authentication.getName());
            vacancyId = vacancyDao.createVacancy(vacancy);
        } catch (Exception e) {
            log.error("Error creating vacancy {}", vacancyDto, e);
            throw e; // rethrow the exception
        }
        return vacancyId;
    }


    @Override
    public void applyForVacancy(String email, int vacancyId) {
        try {
            Optional<User> user = userDao.findByEmail(email);

            vacancyDao.applyForVacancy(user.get(), vacancyId);
        } catch (Exception e) {
            log.error("Error applying for vacancy {}, with user email {}", vacancyId, email, e);
            throw e; // rethrow the exception
        }
    }

    private VacancyDto toDto(Vacancy vacancy) {
        return VacancyDto.builder()
                .id(vacancy.getId())
                .authorEmail(vacancy.getAuthorEmail())
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
                .authorEmail(vacancyDto.getAuthorEmail())
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

    private Vacancy fromCreateDto(VacancyCreateDto vacancyDto, Authentication authentication) {
        return Vacancy.builder()
                .authorEmail(authentication.getName())
                .name(vacancyDto.getName())
                .description(vacancyDto.getDescription())
                .categoryId(vacancyDto.getCategoryId())
                .salary(vacancyDto.getSalary())
                .expFrom(vacancyDto.getExpFrom())
                .createdDate(LocalDate.now())
                .updateTime(LocalDate.now())
                .build();
    }

    private Vacancy fromUpdateDto(VacancyUpdateDto vacancyDto, Authentication authentication) {
        return Vacancy.builder()
                .authorEmail(authentication.getName())
                .name(vacancyDto.getName())
                .description(vacancyDto.getDescription())
                .categoryId(vacancyDto.getCategoryId())
                .salary(vacancyDto.getSalary())
                .expFrom(vacancyDto.getExpFrom())
                .createdDate(LocalDate.now())
                .updateTime(LocalDate.now())
                .build();
    }


}
