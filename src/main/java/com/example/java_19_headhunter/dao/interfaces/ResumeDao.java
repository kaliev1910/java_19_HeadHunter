package com.example.java_19_headhunter.dao.interfaces;

import com.example.java_19_headhunter.models.Resume;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public interface ResumeDao {
    List<Resume> getAll();
     List<Resume> getAll(int perPage, int offset);
    List<Resume> findByCategory(int category);

    List<Resume> findByUserEmail(String userEmail);

    Optional<Resume> findById(int id);

    int create(Resume resume);

    void update(Resume resume);
    public Resume mapRow(ResultSet rs) throws SQLException;
    void deleteById(int id);
    public Integer getCount();
}