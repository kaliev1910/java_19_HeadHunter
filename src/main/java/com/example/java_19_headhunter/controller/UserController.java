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
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    private final ResumeService resumeService;
    private final VacancyServiceImpl vacancyService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserDto userDto) {
        userService.createUser(userDto);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<String> updateProfile(@Valid @RequestBody UserDto userDto) {
        userService.updateUser(userDto);
        return new ResponseEntity<>("Profile updated successfully", HttpStatus.OK);
    }

    @PostMapping("/resume")
    public ResponseEntity<String> createResume(@Valid @RequestBody ResumeDto resumeDto) {
        resumeService.create(resumeDto);
        return new ResponseEntity<>("Resume created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/resume")
    public ResponseEntity<String> updateResume(@Valid @RequestBody ResumeDto resumeDto) {
        resumeService.update(resumeDto);
        return new ResponseEntity<>("Resume updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/resume")
    public ResponseEntity<String> deleteResume(@RequestBody int id) {
        resumeService.deleteById(id);
        return new ResponseEntity<>("Resume deleted successfully", HttpStatus.OK);
    }

    @PostMapping("/vacancy")
    public ResponseEntity<String> createVacancy(@Valid @RequestBody VacancyDto vacancyDto) {
        vacancyService.create(vacancyDto);
        return new ResponseEntity<>("Vacancy created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/vacancy")
    public ResponseEntity<String> updateVacancy(@Valid @RequestBody VacancyDto vacancyDto) {
        vacancyService.update(vacancyDto);
        return new ResponseEntity<>("Vacancy updated successfully", HttpStatus.OK);
    }
}