package com.example.java_19_headhunter.service.interfaces;

import com.example.java_19_headhunter.dto.ResumeUpdateDto;
import com.example.java_19_headhunter.dto.basicDtos.ResumeDto;
import com.example.java_19_headhunter.dto.createDto.ResumeCreateDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.List;


public interface ResumeService {
    Page<ResumeDto> getResumesWithPagingByCategories(Pageable pageable, Integer categoryId);

    List<ResumeDto> getAll();

    List<ResumeDto> findByCategory(int category);


    List<ResumeDto> findByUserEmail(String userEmail);

    ResumeDto findById(int id);



    int create(ResumeCreateDto resumeDto, Authentication authentication);

    void update(@Valid ResumeUpdateDto resumeDto, Authentication authentication);

    public Page<ResumeDto> getResumesWithPaging(Pageable pageable) ;

    void deleteById(int id);

}
