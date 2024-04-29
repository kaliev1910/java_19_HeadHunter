package com.example.java_19_headhunter.dao.interfaces;

import com.example.java_19_headhunter.models.User;
import com.example.java_19_headhunter.models.Vacancy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface VacancyDao {
    List<Vacancy> findAll();

    Integer getCount();

    List<Vacancy> getVacanciesWithPaging(int perPage, int offset);

    Vacancy findById(int id);

    List<Vacancy> findByCategory(int categoryId);

    List<Vacancy> findByUserId(int userId);

    List<User> findRespondedApplicantsForVacancy(int vacancyId);

    List<Vacancy> findActiveVacancies();

    List<Vacancy> findVacanciesBySalaryRange(int salaryFrom, int salaryTo);

    void updateVacancy(Vacancy vacancy);

    int createVacancy(Vacancy vacancy);

    void applyForVacancy(int resumeId, int vacancyId);

    List<Vacancy> findByApplicantEmail(String applicantEmail);
}
