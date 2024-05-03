package com.example.java_19_headhunter.repository;

import com.example.java_19_headhunter.models.Education;
import com.example.java_19_headhunter.models.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EducationRepository  extends JpaRepository<Education,Integer> {
    @Query("select e from Education  e where e.resumeId.id = :resumeId")
    Resume findByResumeId(int resumeId);

    List<Education> findEducationsByResumeId_Id(int resumeId);
}
