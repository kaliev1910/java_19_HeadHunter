package com.example.java_19_headhunter.dao.implementation;

import com.example.java_19_headhunter.dao.interfaces.EducationDao;
import com.example.java_19_headhunter.models.Education;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EducationDaoImpl extends BasicDaoImpl implements EducationDao {

//    @Override
//    public void insert(Education education) {
//        String sql = "INSERT INTO education_info (resume_id, institution, program, degree, start_date, end_date) VALUES (?, ?, ?, ?, ?, ?)";
//        jdbcTemplate.update(sql, education.getResumeId(), education.getInstitution(), education.getProgram(),
//                education.getDegree(), education.getStartDate(), education.getEndDate());
//    }

//    @Override
//    public void update(Education education) {
//        String sql = "UPDATE education_info SET institution = ?, program = ?, degree = ?, start_date = ?, end_date = ? WHERE id = ?";
//        jdbcTemplate.update(sql, education.getInstitution(), education.getProgram(),
//                education.getDegree(), education.getStartDate(), education.getEndDate(), education.getId());
//    }
//
//    @Override
//    public List<Education> findByResumeId(int resumeId) {
//        String sql = "SELECT * FROM education_info WHERE resume_id = ?";
//        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Education.class), resumeId);
//    }
//
//    @Override
//    public void deleteByResumeId(int resumeId) {
//        String sql = "DELETE FROM education_info WHERE resume_id = ?";
//        jdbcTemplate.update(sql, resumeId);
//    }
//
//    @Override
//    public Education findById(int id) {
//        String sql = "SELECT * FROM education_info WHERE id = ?";
//        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Education.class), id);
//    }

}