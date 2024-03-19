package com.example.java_19_headhunter.dao.interfaces;

import com.example.java_19_headhunter.models.Resume;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ResumeDao {
    List<Resume> getAll();

    List<Resume> findByCategory(int category);

    List<Resume> findByUserId(int userId);

    Resume findById(int id);

    void create(Resume resume);

    void update(Resume resume);

    void deleteById(int id);
}