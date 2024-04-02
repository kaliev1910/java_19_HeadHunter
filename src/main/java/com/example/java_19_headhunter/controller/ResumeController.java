package com.example.java_19_headhunter.controller;

import com.example.java_19_headhunter.dto.UserDto;
import com.example.java_19_headhunter.dto.ResumeDto;
import com.example.java_19_headhunter.dto.VacancyDto;
import com.example.java_19_headhunter.service.ResumeService;
import com.example.java_19_headhunter.service.impl.UserServiceImpl;
import com.example.java_19_headhunter.service.impl.VacancyServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resume")
@RequiredArgsConstructor
@PreAuthorize("hasRole('APPLICANT')")
public class ResumeController {
    private final UserServiceImpl userService;
    private final ResumeService resumeService;
    private final VacancyServiceImpl vacancyService;


    @PostMapping("/resume")
    public ResponseEntity<String> createResume(@Valid @RequestBody ResumeDto resumeDto, Authentication authentication) {
        resumeService.create(resumeDto,authentication);
        return new ResponseEntity<>("Resume created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/resume")
    public ResponseEntity<String> updateResume(@Valid @RequestBody ResumeDto resumeDto, Authentication authentication) {
        resumeService.update(resumeDto, authentication);
        return new ResponseEntity<>("Resume updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/resume")
    public ResponseEntity<String> deleteResume(@RequestBody int id) {
        resumeService.deleteById(id);
        return new ResponseEntity<>("Resume deleted successfully", HttpStatus.OK);
    }




    @GetMapping("/resumes/category/{id}")
    public ResponseEntity<List<ResumeDto>> getResumesByCategory(@PathVariable int id) {
        return new ResponseEntity<>(resumeService.findByCategory(id), HttpStatus.OK);
    }

    @GetMapping("/resumes")
    @PreAuthorize("hasRole('applicant')")
    public ResponseEntity<List<ResumeDto>> getAllResumes() {
        return new ResponseEntity<>(resumeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/resumes/email/{email}")
    public ResponseEntity<List<ResumeDto>> getResumesByEmail(@PathVariable String email) {
        return new ResponseEntity<>(resumeService.findByUserEmail(email), HttpStatus.OK);
    }

    @GetMapping("/resumes/{id}")
    public ResponseEntity<ResumeDto> getResumesById(@PathVariable int id) {
        return new ResponseEntity<>(resumeService.findById(id), HttpStatus.OK);
    }


    @GetMapping("/resume/{id}")
    public ResponseEntity<ResumeDto> getResumeById(@PathVariable int id) {
        ResumeDto resume = resumeService.findById(id);
        return new ResponseEntity<>(resume, HttpStatus.OK);
    }
}
