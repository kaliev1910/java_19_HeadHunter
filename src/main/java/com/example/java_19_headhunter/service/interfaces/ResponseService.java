package com.example.java_19_headhunter.service.interfaces;


import com.example.java_19_headhunter.dto.basicDtos.UserResponseDto;
import com.example.java_19_headhunter.models.UserResponse;

import java.util.List;

public interface ResponseService {
    boolean makeResponse(UserResponse response);

    boolean confirmResume(int resumeId, int vacancyId, boolean confirmValue);

    List<UserResponseDto> getEmployerResponses(String email);

    List<UserResponse> getVacancyResponsesByVacancyId(int vacancyId);

    void revokeResponse(int vacancyId, int resumeId);



    List<UserResponse> getResponsesByResumeId(int resumeId);

    List<UserResponseDto> getApplicantResponses(String email);
}
