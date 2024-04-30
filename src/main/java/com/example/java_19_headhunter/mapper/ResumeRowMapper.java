package com.example.java_19_headhunter.mapper;

import com.example.java_19_headhunter.models.Resume;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
public class ResumeRowMapper implements RowMapper<Resume> {

    @Override
    public Resume mapRow(ResultSet rs, int rowNum) throws SQLException {
        Resume resume = new Resume();
        resume.setId(rs.getInt("id"));
        resume.setApplicantEmail(rs.getString("applicant_email"));
        resume.setName(rs.getString("name"));
        resume.setExpectedSalary(rs.getInt("expected_salary"));
        resume.setCategoryId(rs.getInt("category_id"));
        resume.setActive(rs.getBoolean("IS_ACTIVE"));
        resume.setCreatedDate(rs.getTimestamp("created_date"));
        resume.setUpdatedTime(rs.getTimestamp("update_time"));
        return resume;
    }
}