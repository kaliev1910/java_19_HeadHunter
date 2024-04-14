package com.example.java_19_headhunter.dao.implementation;

import com.example.java_19_headhunter.dao.interfaces.ResumeDao;
import com.example.java_19_headhunter.mapper.ResumeRowMapper;
import com.example.java_19_headhunter.models.Resume;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Component
public class ResumeDaoImpl extends BasicDaoImpl implements ResumeDao {
    //TODO добавить методы для пагинации
    //TODO переделать методы чтобы возвращал по ордеру
    @Override
    public List<Resume> findByCategory(int category) {
        String sql = "SELECT * FROM resumes WHERE category_id like ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), category);
    }

    @Override
    public List<Resume> getAll() {
        String sql = "SELECT * FROM resumes  order by  update_time desc ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }

    @Override
    public List<Resume> getAll(int perPage, int offset) {
        String sql = """
            select *
            from resumes
            limit ?
            offset ?;
            """;
        return jdbcTemplate.query(sql, new ResumeRowMapper(), perPage, offset);
    }


    @Override
    public Resume mapRow(ResultSet rs) throws SQLException {
        Resume resume = new Resume();
        resume.setId(rs.getInt("id"));
        resume.setApplicantEmail(rs.getString("applicant_email"));
        resume.setName(rs.getString("name"));
        resume.setExpectedSalary(rs.getInt("expected_salary"));
        resume.setCategoryId(rs.getInt("category_id"));
        resume.setActive(rs.getBoolean("is_active"));
        resume.setCreatedDate(rs.getTimestamp("created_date").toLocalDateTime().toLocalDate());
        resume.setUpdatedTime(rs.getTimestamp("update_time").toLocalDateTime().toLocalDate());
        return resume;
    }
    @Override
    public List<Resume> findByUserEmail(String userEmail) {
        String sql = "SELECT * FROM resumes WHERE APPLICANT_EMAIL = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), userEmail);
    }

    @Override
    public Resume findById(int id) {
        String sql = "SELECT * FROM resumes WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Resume.class), id);
    }

    @Override
    public Integer getCount() {
        String sql = """
                select count(id) from resumes;
                """;
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public int create(Resume resume) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement("INSERT INTO resumes (APPLICANT_EMAIL, name, CATEGORY_ID, expected_salary, IS_ACTIVE, created_date, update_time)" +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)", new String[]{"id"});
            ps.setString(1, resume.getApplicantEmail());
            ps.setString(2, resume.getName());
            ps.setInt(3, resume.getCategoryId());
            ps.setInt(4, resume.getExpectedSalary());
            ps.setBoolean(5, resume.isActive());
            ps.setDate(6, Date.valueOf(resume.getCreatedDate()));
            ps.setDate(7, Date.valueOf(resume.getUpdatedTime()));
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public void update(Resume resume) {
        String sql = "UPDATE resumes SET APPLICANT_EMAIL = ?, name = ?, expected_salary = ?,  created_date = ?, update_time = ? WHERE id = ?";
        jdbcTemplate.update(sql, resume.getApplicantEmail(), resume.getName(), resume.getExpectedSalary(),
                resume.getCreatedDate(), resume.getUpdatedTime(), resume.getId());
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM resumes WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

}
