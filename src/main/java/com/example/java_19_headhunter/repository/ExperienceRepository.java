package com.example.java_19_headhunter.repository;

import com.example.java_19_headhunter.models.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience, Integer> {
    @Query("select e from Experience e where e.resumeId.id = :resumeId")
    List<Experience> findListByResumeId(int resumeId);

    @Query("delete from Experience  e where e.resumeId.id = :resumeId ")
    void deleteEducationsByResumeId(int resumeId);



}
