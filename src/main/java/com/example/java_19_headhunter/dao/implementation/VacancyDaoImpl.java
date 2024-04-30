package com.example.java_19_headhunter.dao.implementation;

import com.example.java_19_headhunter.dao.interfaces.VacancyDao;
import com.example.java_19_headhunter.models.User;
import com.example.java_19_headhunter.models.Vacancy;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Component
public class VacancyDaoImpl extends BasicDaoImpl implements VacancyDao {
    //TODO добавить методы для пагинации
    //TODO переделать методы чтобы возвращал по ордеру
    @Override
    public List<Vacancy> findAll() {
        String sql = "SELECT * FROM vacancies where IS_ACTIVE = true";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }

    @Override
    public Integer getCount() {
        String sql = """
                select count(id) from VACANCIES;
                """;
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public List<Vacancy> getVacanciesWithPaging(int perPage, int offset) {
        String sql = """
                select *
                from VACANCIES order by UPDATE_TIME desc
                limit ?
                offset ?;
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), perPage, offset);
    }

    @Override
    public Optional<Vacancy> findById(int id) {
        String sql = "SELECT * FROM vacancies where ID= ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(
                                sql, new BeanPropertyRowMapper<>(Vacancy.class), id)
                )
        );
    }

    @Override
    public void applyForVacancy(int resumeId, int vacancyId) {
        String sql = "INSERT INTO responded_applicants (resume_id, vacancy_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, resumeId, vacancyId);
    }

    @Override
    public List<Vacancy> findByApplicantEmail(String applicantEmail) {
        String sql = "SELECT * FROM vacancies where AUTHOR_EMAIL = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), applicantEmail);

    }

    @Override
    public List<Vacancy> findByCategory(int categoryId) {
        String sql = "SELECT * FROM vacancies WHERE category_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), categoryId);
    }

    @Override
    public List<Vacancy> findByUserId(int userId) {
        String sql = "SELECT * FROM vacancies WHERE id IN (SELECT vacancy_id FROM responded_applicants WHERE resume_id = ?)";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), userId);
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
        String sql = """ 
                UPDATE vacancies SET  name = ?, description = ?, category_id = ?,
                                salary = ?, exp_from = ?, exp_to = ?, is_active = ?, update_time = ? WHERE id = ?;
                """;
        jdbcTemplate.update(sql, vacancy.getName(), vacancy.getDescription(), vacancy.getCategoryId(),
                vacancy.getSalary(), vacancy.getExpFrom(), vacancy.getExpTo(), vacancy.isActive(),
                vacancy.getUpdateTime(), vacancy.getId());
    }

    @Override
    public int createVacancy(Vacancy vacancy) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement("INSERT INTO vacancies (AUTHOR_EMAIL, name, description, category_id, salary, exp_from, exp_to, is_active, update_time) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", new String[]{"id"});
            ps.setString(1, vacancy.getAuthorEmail());
            ps.setString(2, vacancy.getName());
            ps.setString(3, vacancy.getDescription());
            ps.setInt(4, vacancy.getCategoryId());
            ps.setInt(5, vacancy.getSalary());
            ps.setInt(6, vacancy.getExpFrom());
            ps.setInt(7, vacancy.getExpTo());
            ps.setBoolean(8, vacancy.isActive());
            ps.setTimestamp(9, vacancy.getCreatedDate());
            return ps;
        }, keyHolder);
        return (int) keyHolder.getKey();
    }
}
