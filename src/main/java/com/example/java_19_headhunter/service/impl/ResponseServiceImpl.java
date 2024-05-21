package com.example.java_19_headhunter.service.impl;

import com.example.java_19_headhunter.models.UserResponse;
import com.example.java_19_headhunter.repository.ResumeRepository;
import com.example.java_19_headhunter.repository.UserResponseRepository;
import com.example.java_19_headhunter.repository.VacancyRepository;
import com.example.java_19_headhunter.service.interfaces.ResponseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ResponseServiceImpl implements ResponseService {

    private final UserResponseRepository userResponseRepository;
    private final ResumeRepository resumeRepository;
    private final VacancyRepository vacancyRepository;

    @Override
    public boolean makeResponse(int resumeId, int vacancyId) {
        UserResponse  response;
        try {
            response =  UserResponse.builder()
                    .resumeId(resumeRepository.findResumeById(resumeId).orElseThrow())
                    .vacancyId(vacancyRepository.findById(vacancyId).orElseThrow())
                    .build();
            userResponseRepository.save(response);
            return true;
        } catch (Exception e) {
            log.info("Error making response ");
            return false;
        }
    }

//    @Override
//    public boolean confirmResume(int resumeId, int vacancyId, boolean confirmValue) {
//        try {
//            confirmResume(resumeId, vacancyId, confirmValue);
//            return true;
//        } catch (Exception e) {
//            log.info("Error confirming resume");
//            return false;
//        }
//    }

//    @Override
//    public List<UserResponse> getEmployerResponses(String email) {
//        try {
//            return userResponseRepository.findByEmail(email);
//        } catch (Exception e) {
//            log.info("Error getting employer responses: {}", e.toString());
//            return null;
//        }
//
//    }

    @Override
    public List<UserResponse> getVacancyResponsesByVacancyId(int vacancyId) {
        try {
            return userResponseRepository.findUserResponsesByVacancyId_id(vacancyId);
        } catch (Exception e) {
            log.info("Error getting Vacancy responses by Vacancy ID: {}", e.toString());
            return null;
        }

    }

    @Override
    public void revokeResponse(int vacancyId, int resumeId) {
        try {
            userResponseRepository.deleteUserResponseByVacancyId_IdAndResumeId_Id(vacancyId, resumeId);
        } catch (Exception e) {
            log.info("Error revoking response: {}", e.toString());
        }
    }

    @Override
    public List<UserResponse> getResponsesByResumeId(int resumeId) {
        try {
            return userResponseRepository.findUserResponsesByResumeId_id(resumeId);

        } catch (Exception e) {
            log.info("Error getting responses by Resume ID: {}", e.toString());
            return null;
        }

    }
//
    @Override
    public List<UserResponse> getUserResponses(String email) {
        try {
            return userResponseRepository.getApplicantResponses(email);
        } catch (Exception e) {

            log.info("Error getting applicant responses: {}", e.toString());
            return null;
        }
    }


}
