package com.example.java_19_headhunter.dao.interfaces;

import com.example.java_19_headhunter.models.UserResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ResponseDao {
    void applyForVacancy(UserResponse response);

    void confirmResume(int resumeId, int vacancyId, boolean confirmValue);

    List<UserResponse> getEmployerResponses(String email);

    List<UserResponse> getVacancyResponsesByVacancyId(int vacancyId);

    void revokeResponse(int vacancyId, int resumeId);



    List<UserResponse> getResponsesByResumeId(int resumeId);

    List<UserResponse> getApplicantResponses(String email);
}
