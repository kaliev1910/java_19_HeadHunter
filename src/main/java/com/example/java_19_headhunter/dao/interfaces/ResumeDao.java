package com.example.java_19_headhunter.dao.interfaces;

import com.example.java_19_headhunter.models.Resume;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ResumeDao {
    List<Resume> getAll();

    List<Resume> findByCategory(int category);

    List<Resume> findByUserEmail(String userEmail);

    Resume findById(int id);

    int create(Resume resume);

    void update(Resume resume);

    void deleteById(int id);
}