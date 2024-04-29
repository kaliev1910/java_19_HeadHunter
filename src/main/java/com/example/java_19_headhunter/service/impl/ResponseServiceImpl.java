package com.example.java_19_headhunter.service.impl;

import com.example.java_19_headhunter.dao.interfaces.ResponseDao;
import com.example.java_19_headhunter.models.UserResponse;
import com.example.java_19_headhunter.service.interfaces.ResponseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ResponseServiceImpl implements ResponseService {

    private final ResponseDao responseDao;

    @Override
    public boolean makeResponse(UserResponse response) {
        try {
            responseDao.applyForVacancy(response);
            return true;
        } catch (Exception e) {
            log.info("Error making response for response: {}", response.toString());
            return false;
        }
    }

    @Override
    public boolean confirmResume(int resumeId, int vacancyId, boolean confirmValue) {
        try {
            confirmResume(resumeId, vacancyId, confirmValue);
            return true;
        } catch (Exception e) {
            log.info("Error confirming resume");
            return false;
        }
    }

    @Override
    public List<UserResponse> getEmployerResponses(String email) {
        try {
            return responseDao.getEmployerResponses(email);
        } catch (Exception e) {
            log.info("Error getting employer responses: {}", e.toString());
            return null;
        }

    }

    @Override
    public List<UserResponse> getVacancyResponsesByVacancyId(int vacancyId) {
        try {
            return responseDao.getVacancyResponsesByVacancyId(vacancyId);
        } catch (Exception e) {
            log.info("Error getting Vacancy responses by Vacancy ID: {}", e.toString());
            return null;
        }

    }

    @Override
    public void revokeResponse(int vacancyId, int resumeId) {
        try {
            responseDao.revokeResponse(vacancyId, resumeId);
        } catch (Exception e) {
            log.info("Error revoking response: {}", e.toString());
        }
    }

    @Override
    public List<UserResponse> getResponsesByResumeId(int resumeId) {
        try {
            return responseDao.getResponsesByResumeId(resumeId);

        } catch (Exception e) {
            log.info("Error getting responses by Resume ID: {}", e.toString());
            return null;
        }

    }

    @Override
    public List<UserResponse> getApplicantResponses(String email) {
        try {
            return responseDao.getApplicantResponses(email);
        } catch (Exception e) {

            log.info("Error getting applicant responses: {}", e.toString());
            return null;
        }
    }
}
