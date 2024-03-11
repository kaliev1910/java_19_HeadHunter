package com.example.java_19_headhunter.dao.implementation;

import com.example.java_19_headhunter.dao.BasicDao;
import com.example.java_19_headhunter.dao.ResumeDao;
import com.example.java_19_headhunter.models.Resume;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResumeDaoImpl extends BasicDao implements ResumeDao {
    @Override
    public List<Resume> findByCategory(int category) {
        String sql = "SELECT * FROM resumes WHERE category_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), category);
    }

    @Override
    public List<Resume> findByUserId(int userId) {
        return null;
    }
}
