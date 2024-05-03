package com.example.java_19_headhunter.service.impl;

import com.example.java_19_headhunter.dao.interfaces.UserDao;
import com.example.java_19_headhunter.dao.interfaces.VacancyDao;
import com.example.java_19_headhunter.dto.basicDtos.VacancyDto;
import com.example.java_19_headhunter.dto.createDto.VacancyCreateDto;
import com.example.java_19_headhunter.dto.updateDto.VacancyUpdateDto;
import com.example.java_19_headhunter.exeptions.VacancyNotFoundException;
import com.example.java_19_headhunter.models.User;
import com.example.java_19_headhunter.models.Vacancy;
import com.example.java_19_headhunter.repository.CategoryRepository;
import com.example.java_19_headhunter.repository.VacancyRepository;
import com.example.java_19_headhunter.service.interfaces.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor


public class VacancyServiceImpl implements VacancyService {
    private final VacancyDao vacancyDao;
    private final UserDao userDao;

    private final VacancyRepository vacancyRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<VacancyDto> findAll() {
        try {
            return vacancyRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error finding all vacancies", e);
            throw e; // rethrow the exception
        }
    }

    public List<Vacancy> getVacanciesWithPagingShort(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("updatedTime"));
        return vacancyRepository.findAll(pageable).getContent();
    }

    @Override
    public List<VacancyDto> getVacanciesWithPaging(Integer page, Integer pageSize) {
        long count = vacancyRepository.count();
        int totalPages = (int) Math.ceil((double) count / pageSize);

        if (totalPages <= page) {
            page = totalPages - 1;
        } else if (page < 0) {
            page = 0;
        }

        List<VacancyDto> vacancyDtos = new ArrayList<>();
        List<Vacancy> list = getVacanciesWithPagingShort(page, pageSize);

        list.forEach(e -> vacancyDtos.add(VacancyDto.builder()
                .id(e.getId())
                .authorEmail(e.getAuthorEmail().getEmail())
                .name(e.getName())
                .description(e.getDescription())
                .categoryId(e.getCategoryId().getId())
                .salary(e.getSalary())
                .expFrom(e.getExpFrom())
                .expTo(e.getExpTo())
                .isActive(e.isActive())
                .createdDate(e.getCreatedDate())
                .updateTime(e.getUpdatedTime())
                .build()));

        return vacancyDtos;
    }


    @Override
    public VacancyDto findById(int id) {
        return toDto(vacancyRepository.findById(id).orElseThrow(() -> new VacancyNotFoundException("Vacancy Not Found")));
    }

    @Override
    public List<VacancyDto> findByCategory(int categoryId) {
        try {
            return vacancyRepository.findVacanciesByCategoryId_Id(categoryId).stream().map(this::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error finding vacancies by category {}", categoryId, e);
            throw e; // rethrow the exception
        }
    }

    @Override
    public List<VacancyDto> findByUserId(int userId) {
        try {
            return vacancyRepository.findActiveVacanciesByAuthorEmail_id(userId).stream().map(this::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error finding vacancies by userId {}", userId, e);
            throw e; // rethrow the exception
        }
    }

    @Override
    public List<VacancyDto> findByUserEmail(String applicantEmail) {
        try {
            return vacancyRepository.findVacanciesByAuthorEmail_Email(applicantEmail).stream().map(this::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error finding vacancies by applicantEmail {}", applicantEmail, e);
            throw e; // rethrow the exception
        }
    }

    @Override
    public List<VacancyDto> findActiveVacancies() {
        try {
            return vacancyRepository.findActiveVacancies().stream().map(this::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error finding active vacancies", e);
            throw e; // rethrow the exception
        }
    }

    @Override
    public List<VacancyDto> findVacanciesBySalaryRange(int salaryFrom, int salaryTo) {
        try {
            return vacancyRepository.findVacanciesBySalaryBetween(salaryFrom, salaryTo).stream().map(this::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error finding vacancies by salary range {} - {}", salaryFrom, salaryTo, e);
            throw e; // rethrow the exception
        }
    }

    @Override
    public void update(VacancyUpdateDto vacancyDto, Authentication a) {
        try {
            vacancyDto.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));
            vacancyRepository.save(fromUpdateDto(vacancyDto, a));
        } catch (Exception e) {
            log.error("Error updating vacancy {}", vacancyDto, e);
            throw e; // rethrow the exception
        }
    }

    @Override
    public int create(VacancyCreateDto vacancyDto, Authentication authentication) {
        Vacancy vacancyId;
        try {
            Vacancy vacancy = fromCreateDto(vacancyDto, authentication);
            vacancy.getAuthorEmail().setEmail(authentication.getName());
            vacancyId = vacancyRepository.save(vacancy);
        } catch (Exception e) {
            log.error("Error creating vacancy {}", vacancyDto, e);
            throw e; // rethrow the exception
        }
        return vacancyId.getId();
    }


    @Override
    public void applyForVacancy(int resumeId, int vacancyId) {
        try {
            vacancyDao.applyForVacancy(resumeId, vacancyId);
        } catch (Exception e) {
            log.error("Error applying for vacancy {}, with resume {}", vacancyId, resumeId, e);
            throw e; // rethrow the exception
        }
    }

    private VacancyDto toDto(Vacancy vacancy) {
        return VacancyDto.builder()
                .id(vacancy.getId())
                .authorEmail(vacancy.getAuthorEmail().getEmail())
                .name(vacancy.getName())
                .description(vacancy.getDescription())
                .categoryId(vacancy.getCategoryId().getId())
                .salary(vacancy.getSalary())
                .expFrom(vacancy.getExpFrom())
                .expTo(vacancy.getExpTo())
                .isActive(vacancy.isActive())
                .createdDate(vacancy.getCreatedDate())
                .updateTime(vacancy.getUpdatedTime())
                .build();
    }

    private Vacancy fromDto(VacancyDto vacancyDto) {
        return Vacancy.builder()
                .id(vacancyDto.getId())
                .authorEmail(vacancyRepository.findVacancyByAuthorEmail_Email(vacancyDto.getAuthorEmail()).get().getAuthorEmail())
                .name(vacancyDto.getName())
                .description(vacancyDto.getDescription())
                .categoryId(categoryRepository.findById(vacancyDto.getCategoryId()).get())
                .salary(vacancyDto.getSalary())
                .expFrom(vacancyDto.getExpFrom())
                .expTo(vacancyDto.getExpTo())
                .isActive(vacancyDto.isActive())
                .createdDate(vacancyDto.getCreatedDate())
                .updatedTime(vacancyDto.getUpdateTime())
                .build();
    }

    private Vacancy fromCreateDto(VacancyCreateDto vacancyDto, Authentication authentication) {
        return Vacancy.builder()
                .authorEmail((User) authentication.getPrincipal())
                .name(vacancyDto.getName())
                .description(vacancyDto.getDescription())
                .categoryId(categoryRepository.findById(vacancyDto.getCategoryId()).get())
                .salary(vacancyDto.getSalary())
                .expFrom(vacancyDto.getExpFrom())
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .updatedTime(Timestamp.valueOf(LocalDateTime.now()))
                .build();
    }

    private Vacancy fromUpdateDto(VacancyUpdateDto vacancyDto, Authentication authentication) {
        return Vacancy.builder()
                .authorEmail(vacancyRepository.findVacancyByAuthorEmail_Email(authentication.getName()).get().getAuthorEmail())
                .name(vacancyDto.getName())
                .description(vacancyDto.getDescription())
                .categoryId(categoryRepository.findById(vacancyDto.getCategoryId()).get())
                .salary(vacancyDto.getSalary())
                .expFrom(vacancyDto.getExpFrom())
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .updatedTime(Timestamp.valueOf(LocalDateTime.now()))
                .build();
    }


}
