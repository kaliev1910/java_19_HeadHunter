package com.example.java_19_headhunter.dao.implementation;

import com.example.java_19_headhunter.dao.interfaces.ExperienceDao;
import com.example.java_19_headhunter.models.Experience;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ExperienceDaoImpl extends BasicDaoImpl implements ExperienceDao {
    @Override
    public void insert(Experience experience) {
        String sql = "INSERT INTO work_experience_info (resume_id, company_name, position, responsibilities, years) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, experience.getResumeId(), experience.getCompanyName(), experience.getPosition(), experience.getResponsibilities(), experience.getYears());
    }

    @Override
    public void update(Experience experience) {
        String sql = "UPDATE work_experience_info SET company_name = ?, position = ?, responsibilities = ?, years = ? WHERE id = ?";
        jdbcTemplate.update(sql, experience.getCompanyName(), experience.getPosition(), experience.getResponsibilities(), experience.getYears(), experience.getId());
    }

    @Override
    public List<Experience> findByResumeId(int resumeId) {
        String sql = "SELECT * FROM work_experience_info WHERE resume_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Experience.class), resumeId);
    }

    @Override
    public void deleteByResumeId(int resumeId) {
        String sql = "DELETE FROM work_experience_info WHERE resume_id = ?";
        jdbcTemplate.update(sql, resumeId);
    }


}
