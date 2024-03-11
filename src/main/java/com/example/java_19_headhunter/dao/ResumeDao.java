package com.example.java_19_headhunter.dao;

import com.example.java_19_headhunter.models.Category;
import com.example.java_19_headhunter.models.Resume;

import java.util.List;

public interface ResumeDao {
    List<Resume> findByCategory(int category);
    List<Resume> findByUserId(int userId);
}
