package com.example.java_19_headhunter.dao.interfaces;

import com.example.java_19_headhunter.models.Experience;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ExperienceDao {
    void insert(Experience experience);

    void update(Experience experience);

    List<Experience> findByResumeId(int resumeId);

    void deleteByResumeId(int resumeId);
}
