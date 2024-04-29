package com.example.java_19_headhunter.dao.implementation;

import com.example.java_19_headhunter.dao.interfaces.ResponseDao;
import com.example.java_19_headhunter.models.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ResponseDaoImpl extends BasicDaoImpl implements ResponseDao {

    KeyHolder keyHolder;

    @Override
    public void applyForVacancy(UserResponse response) {
        String sql = """ 
                insert into responded_applicants (resume_id, vacancy_id)
                values (?,?)
                """;
        jdbcTemplate.update(sql, response.getResumeId(), response.getVacancyId());
    }

    @Override
    public void confirmResume(int resumeId, int vacancyId, boolean confirmValue) {
        String sql = """ 
                update responded_applicants set confirmation = ? where resume_id = ? and vacancy_id = ?
                """;
        jdbcTemplate.update(sql, confirmValue, vacancyId, resumeId);
    }

    @Override
    public List<UserResponse> getEmployerResponses(String email) {
        String sql = """ 
                SELECT * FROM responded_applicants
                WHERE resume_id IN (
                    SELECT id
                    FROM vacancies
                    WHERE vacancies.author_email = ?
                    );
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UserResponse.class), email);
    }

    @Override
    public List<UserResponse> getVacancyResponsesByVacancyId(int vacancyId) {
        String sql = """ 
                select * from responded_applicants where vacancy_id = ?
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UserResponse.class), vacancyId);
    }

    @Override
    public void revokeResponse(int vacancyId, int resumeId) {
        String sql = """ 
                delete from responded_applicants where  resume_id = ? and vacancy_id = ?
                """;
        jdbcTemplate.update(sql, vacancyId, resumeId);
    }
    @Override
    public List<UserResponse> getResponsesByResumeId(int resumeId){
        String sql = """
                select * from RESPONDED_APPLICANTS where RESUME_ID = ?
                """;
        return  jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UserResponse.class), resumeId);
    }

    @Override
    public List<UserResponse> getApplicantResponses(String email) {
        String sql = """ 
                SELECT * FROM responded_applicants
                WHERE resume_id IN (
                    SELECT id
                    FROM resumes
                    WHERE applicant_email = ?
                    );
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UserResponse.class), email);
    }
}
