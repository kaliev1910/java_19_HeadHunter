package com.example.java_19_headhunter.repository;

import com.example.java_19_headhunter.models.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserResponseRepository extends JpaRepository<UserResponse, Integer> {

    List<UserResponse> findUserResponsesByVacancyId_id(int vacancyId);

    void deleteUserResponseByVacancyId_IdAndResumeId_Id(int vacancyId, int resumeId);

    List<UserResponse> findUserResponsesByResumeId_id(int resumeId);


    @Query("SELECT ra FROM UserResponse ra WHERE ra.resumeId.id IN (SELECT r.id FROM Resume r WHERE r.applicantEmail = :email)")
    List<UserResponse> getApplicantResponses(String email);
}
