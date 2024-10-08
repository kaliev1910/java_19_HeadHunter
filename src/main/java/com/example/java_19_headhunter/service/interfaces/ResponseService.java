package com.example.java_19_headhunter.service.interfaces;


import com.example.java_19_headhunter.models.UserResponse;

import java.util.List;

public interface ResponseService {
    boolean makeResponse(int vacancyId, int resumeId);

//    boolean confirmResume(int resumeId, int vacancyId, boolean confirmValue);

//    List<UserResponse> getEmployerResponses(String email);

    List<UserResponse> getVacancyResponsesByVacancyId(int vacancyId);

    void revokeResponse(int vacancyId, int resumeId);



    List<UserResponse> getResponsesByResumeId(int resumeId);

    List<UserResponse> getUserResponses(String email);
}
