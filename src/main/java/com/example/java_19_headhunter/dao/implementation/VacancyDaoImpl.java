package com.example.java_19_headhunter.dao.implementation;

import com.example.java_19_headhunter.dao.VacancyDao;
import com.example.java_19_headhunter.models.Category;
import com.example.java_19_headhunter.models.User;
import com.example.java_19_headhunter.models.Vacancy;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class VacancyDaoImpl implements VacancyDao {
    // Implementation of VacancyDao methods
    @Override
    public List<Vacancy> findAll() {
        // Implementation to find all vacancies
        return null;
    }

    @Override
    public List<Vacancy> findByCategory(Category category) {
        // Implementation to find vacancies by category
        return null;
    }

    @Override
    public List<Vacancy> findByApplicant(long applicantId) {
        // Implementation to find vacancies by applicant ID
        return null;
    }

    @Override
    public List<User> findRespondedApplicantsForVacancy(long vacancyId) {
        // Implementation to find applicants for a vacancy
        return null;
    }
}