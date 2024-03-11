package com.example.java_19_headhunter.dao.implementation;

import com.example.java_19_headhunter.dao.BasicDao;
import com.example.java_19_headhunter.dao.VacancyDao;
import com.example.java_19_headhunter.models.Category;
import com.example.java_19_headhunter.models.User;
import com.example.java_19_headhunter.models.Vacancy;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class VacancyDaoImpl extends BasicDao implements VacancyDao {

    @Override
    public List<Vacancy> findAll() {
        String sql = "SELECT * FROM vacancies";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }

    @Override
    public List<Vacancy> findByCategory(int categoryId) {
        String sql = "SELECT * FROM vacancies WHERE category_id = ?";
        return jdbcTemplate.query(sql,  new BeanPropertyRowMapper<>(Vacancy.class),categoryId);
    }

    @Override
    public List<Vacancy> findByApplicantId(int applicantId) {
        String sql = "SELECT * FROM vacancies WHERE id IN (SELECT vacancy_id FROM responded_applicants WHERE resume_id = ?)";
        return jdbcTemplate.query(sql,  new BeanPropertyRowMapper<>(Vacancy.class), applicantId);
    }

    @Override
    public List<User> findRespondedApplicantsForVacancy(int vacancyId) {
        String sql = "SELECT * FROM users WHERE id IN (SELECT resume_id FROM responded_applicants WHERE vacancy_id = ?)";
        return jdbcTemplate.query(sql,  new BeanPropertyRowMapper<>(User.class), vacancyId);
    }
}