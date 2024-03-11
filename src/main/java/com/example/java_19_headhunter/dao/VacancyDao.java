package com.example.java_19_headhunter.dao;

import com.example.java_19_headhunter.models.Category;
import com.example.java_19_headhunter.models.User;
import com.example.java_19_headhunter.models.Vacancy;

import java.util.List;

public interface VacancyDao {
    List<Vacancy> findAll();
    List<Vacancy> findByCategory(Category category);
    List<Vacancy> findByApplicant(long applicantId);
    List<User> findRespondedApplicantsForVacancy(long vacancyId);
}
