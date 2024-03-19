package com.example.java_19_headhunter.controller;

import com.example.java_19_headhunter.dto.UserDto;
import com.example.java_19_headhunter.dto.ResumeDto;
import com.example.java_19_headhunter.dto.VacancyDto;
import com.example.java_19_headhunter.service.ResumeService;
import com.example.java_19_headhunter.service.UserService;
import com.example.java_19_headhunter.service.VacancyService;
import com.example.java_19_headhunter.service.impl.UserServiceImpl;
import com.example.java_19_headhunter.service.impl.VacancyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("applicant")
@RequiredArgsConstructor
public class ApplicantController {
    private final UserServiceImpl userService;
    private final  ResumeService resumeService;
    private final VacancyServiceImpl vacancyService;

    @PostMapping("/register")
    public void register(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
    }

    @PutMapping("/update")
    public void updateProfile(@RequestBody UserDto userDto) {
        userService.updateUser(userDto);
    }

    @PostMapping("/resume")
    public void createResume(@RequestBody ResumeDto resumeDto) {
        resumeService.create(resumeDto);
    }

    @PutMapping("/resume")
    public void updateResume(@RequestBody ResumeDto resumeDto) {
        resumeService.update(resumeDto);
    }

//    @GetMapping("/resumes")
//    public List<ResumeDto> getResumes() {
//        return resumeService.getResumes();
//    }
//
//    @GetMapping("/vacancies")
//    public List<VacancyDto> findVacancies() {
//        return vacancyService.findVacancies();
//    }
//
//    @PostMapping("/apply/{vacancyId}")
//    public void applyForJob(@PathVariable int vacancyId) {
//        vacancyService.applyForJob(vacancyId);
//    }
}