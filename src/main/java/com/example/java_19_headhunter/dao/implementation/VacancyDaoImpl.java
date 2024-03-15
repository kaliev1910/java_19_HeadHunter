package com.example.java_19_headhunter.dao.implementation;

import com.example.java_19_headhunter.dao.interfaces.VacancyDao;
import com.example.java_19_headhunter.models.User;
import com.example.java_19_headhunter.models.Vacancy;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VacancyDaoImpl extends BasicDaoImpl implements VacancyDao {

    @Override
    public List<Vacancy> findAll() {
        String sql = "SELECT * FROM vacancies";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }

    @Override
    public List<Vacancy> findByCategory(int categoryId) {
        String sql = "SELECT * FROM vacancies WHERE category_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), categoryId);
    }

    @Override
    public List<Vacancy> findByApplicantId(int applicantId) {
        String sql = "SELECT * FROM vacancies WHERE id IN (SELECT vacancy_id FROM responded_applicants WHERE resume_id = ?)";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), applicantId);
    }

    @Override
    public List<User> findRespondedApplicantsForVacancy(int vacancyId) {
        String sql = "SELECT * FROM users WHERE id IN (SELECT resume_id FROM responded_applicants WHERE vacancy_id = ?)";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), vacancyId);
    }

    @Override
    public List<Vacancy> findActiveVacancies() {
        String sql = "SELECT * FROM vacancies WHERE is_active = true";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }

    @Override
    public List<Vacancy> findVacanciesBySalaryRange(int salaryFrom, int salaryTo) {
        String sql = "SELECT * FROM vacancies WHERE salary BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), salaryFrom, salaryTo);
    }

    @Override
    public void updateVacancy(Vacancy vacancy) {
        String sql = "UPDATE vacancies SET author_id = ?, name = ?, description = ?, category_id = ?, " +
                "salary = ?, exp_from = ?, exp_to = ?, is_active = ?, update_time = ? WHERE id = ?";
        jdbcTemplate.update(sql, vacancy.getAuthorId(), vacancy.getName(), vacancy.getDescription(), vacancy.getCategoryId(),
                vacancy.getSalary(), vacancy.getExpFrom(), vacancy.getExpTo(), vacancy.isActive(),
                vacancy.getUpdateTime(), vacancy.getId());
    }

    @Override
    public void createVacancy(Vacancy vacancy) {
        String sql = "INSERT INTO vacancies (author_id, name, description, category_id, salary, exp_from, exp_to, is_active, created_date, update_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, vacancy.getAuthorId(), vacancy.getName(), vacancy.getDescription(), vacancy.getCategoryId(),
                vacancy.getSalary(), vacancy.getExpFrom(), vacancy.getExpTo(), vacancy.isActive(),
                vacancy.getCreatedDate(), vacancy.getUpdateTime());
    }

}
