package com.example.java_19_headhunter.dao.implementation;

import com.example.java_19_headhunter.dao.interfaces.ResumeDao;
import com.example.java_19_headhunter.models.Resume;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResumeDaoImpl extends BasicDaoImpl implements ResumeDao {
    @Override
    public List<Resume> findByCategory(int category) {
        String sql = "SELECT * FROM resumes WHERE category_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), category);
    }

    @Override
    public List<Resume> findByUserId(int userId) {
        String sql = "SELECT * FROM resumes WHERE applicant_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), userId);
    }


    @Override
    public Resume findById(int id) {
        String sql = "SELECT * FROM resumes WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Resume.class), id);
    }

    @Override
    public void insert(Resume resume) {
        String sql = "INSERT INTO resumes (id, applicant_id, name, expected_salary,  created_date, update_time) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
         jdbcTemplate.update(sql, resume.getId(), resume.getApplicantId(), resume.getName(),
                resume.getExpectedSalary(), resume.getCreatedTime(), resume.getUpdatedTime());
    }

    @Override
    public void update(Resume resume) {
        String sql = "UPDATE resumes SET applicant_id = ?, name = ?, expected_salary = ?,  created_date = ?, update_time = ? WHERE id = ?";
         jdbcTemplate.update(sql, resume.getApplicantId(), resume.getName(), resume.getExpectedSalary(),
                resume.getCreatedTime(), resume.getUpdatedTime(), resume.getId());
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM resumes WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
