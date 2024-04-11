package com.example.java_19_headhunter.service;

import com.example.java_19_headhunter.dto.basicDtos.ResumeDto;
import com.example.java_19_headhunter.dto.createDto.ResumeCreateDto;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;

import java.util.List;


public interface ResumeService {
    List<ResumeDto> getAll();
    List<ResumeDto> findByCategory(int category);


    List<ResumeDto> findByUserEmail(String userEmail);

    ResumeDto findById(int id);


    @PreAuthorize("hasAuthority('APPLICANT')")
    int create(ResumeCreateDto resumeDto, Authentication authentication);

    void update(@Valid ResumeCreateDto resumeDto, Authentication authentication);
    void deleteById(int id);

}
