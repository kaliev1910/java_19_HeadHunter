package com.example.java_19_headhunter.dao.interfaces;

import com.example.java_19_headhunter.models.User;
import com.example.java_19_headhunter.models.Vacancy;

import java.util.List;

public interface VacancyDao {
    List<Vacancy> findAll();
    List<Vacancy> findByCategory(int categoryId);
    List<Vacancy> findByApplicantId(int applicantId);
    List<User> findRespondedApplicantsForVacancy(int vacancyId);
}
