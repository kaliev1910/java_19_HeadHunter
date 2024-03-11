package com.example.java_19_headhunter.dao.implementation;

import com.example.java_19_headhunter.dao.ResumeDao;
import com.example.java_19_headhunter.models.Category;
import com.example.java_19_headhunter.models.Resume;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ResumeDaoImpl implements ResumeDao {
    // Implementation of ResumeDao methods
    @Override
    public List<Resume> findByCategory(Category category) {
        // Implementation to find resumes by category
        return null;
    }

    @Override
    public List<Resume> findByUserId(long userId) {
        // Implementation to find resumes by user ID
        return null;
    }
}
