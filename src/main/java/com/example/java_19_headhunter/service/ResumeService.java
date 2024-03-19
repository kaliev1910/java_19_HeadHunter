package com.example.java_19_headhunter.service;

import com.example.java_19_headhunter.dto.ResumeDto;

import java.util.List;


public interface ResumeService {
    List<ResumeDto> findByCategory(int category);
    List<ResumeDto> findByUserId(int userId);
    ResumeDto findById(int id);
    void create(ResumeDto resumeDto);
    void update(ResumeDto resumeDto);
    void deleteById(int id);
}
