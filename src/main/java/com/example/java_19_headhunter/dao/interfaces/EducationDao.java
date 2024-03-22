package com.example.java_19_headhunter.dao.interfaces;

import com.example.java_19_headhunter.models.Education;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface EducationDao {
    void insert(Education education);
    void update(Education education);
    List<Education> findByResumeId(int resumeId);
    void deleteByResumeId(int resumeId);
}
