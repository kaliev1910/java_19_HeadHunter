package com.example.java_19_headhunter.controller;

import com.example.java_19_headhunter.dto.UserDto;
import com.example.java_19_headhunter.dto.ResumeDto;
import com.example.java_19_headhunter.dto.VacancyDto;
import com.example.java_19_headhunter.service.ResumeService;
import com.example.java_19_headhunter.service.impl.UserServiceImpl;
import com.example.java_19_headhunter.service.impl.VacancyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    private final ResumeService resumeService;
    private final VacancyServiceImpl vacancyService;

    @PostMapping("/register")
    public HttpStatus register(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
        return HttpStatus.OK;

    }

    @PutMapping("")
    public HttpStatus updateProfile(@RequestBody UserDto userDto) {
        userService.updateUser(userDto);
        return HttpStatus.OK;

    }

    @PostMapping("/resume")
    public HttpStatus createResume(@RequestBody ResumeDto resumeDto) {
        resumeService.create(resumeDto);
        return HttpStatus.OK;

    }

    @PutMapping("/resume")
    public HttpStatus updateResume(@RequestBody ResumeDto resumeDto) {
        resumeService.update(resumeDto);
        return HttpStatus.OK;
    }

    @DeleteMapping("/resume")
    public HttpStatus deleteResume(@RequestBody int id) {
        resumeService.deleteById(id);
        return HttpStatus.OK;
    }


    @GetMapping("/resumes")
    public List<ResumeDto> getResumes() {
        return resumeService.getAll();
    }

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/vacancies")
    public List<VacancyDto> findVacancies() {
        return vacancyService.findAll();
    }

    @PostMapping("/apply/")
    public void applyForJob(@RequestBody String email, int vacancyId) {
        vacancyService.applyForVacancy(email, vacancyId);
    }

    @PostMapping("/vacancy")
    public HttpStatus createVacancy(@RequestBody VacancyDto vacancyDto) {
        vacancyService.create(vacancyDto);
        return HttpStatus.OK;
    }

    @PutMapping("/vacancy")
    public HttpStatus updateVacancy(@RequestBody VacancyDto vacancyDto) {
        vacancyService.update(vacancyDto);
        return HttpStatus.OK;

    }


}