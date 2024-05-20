package com.example.java_19_headhunter.repository;

import com.example.java_19_headhunter.models.Experience;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience, Integer> {
    @Query("select e from Experience e where e.resumeId.id = :resumeId")
    List<Experience> findListByResumeId(int resumeId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM WORK_EXPERIENCE_INFO WHERE resume_id = :resumeId", nativeQuery = true)
    void deleteAllByResumeId_Id(@Param("resumeId") int resumeId);


}
