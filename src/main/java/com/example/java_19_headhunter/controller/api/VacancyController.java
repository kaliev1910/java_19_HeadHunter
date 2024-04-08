package com.example.java_19_headhunter.controller.api;

import com.example.java_19_headhunter.dto.basicDtos.VacancyDto;
import com.example.java_19_headhunter.service.ResumeService;
import com.example.java_19_headhunter.service.impl.UserServiceImpl;
import com.example.java_19_headhunter.service.impl.VacancyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class VacancyController {
    private final UserServiceImpl userService;
    private final ResumeService resumeService;
    private final VacancyServiceImpl vacancyService;


    @GetMapping("/vacancies")
    public ResponseEntity<List<VacancyDto>> getAllVacancies() {
        return new ResponseEntity<>(vacancyService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/vacancies/category/{id}")
    public ResponseEntity<List<VacancyDto>> getVacanciesByCategory(@PathVariable int id) {
        return new ResponseEntity<>(vacancyService.findByCategory(id), HttpStatus.OK);
    }

    @GetMapping("/vacancies/applicant/{id}")
    public ResponseEntity<List<VacancyDto>> getVacanciesByApplicantId(@PathVariable int id) {
        return new ResponseEntity<>(vacancyService.findByApplicantId(id), HttpStatus.OK);
    }

    @GetMapping("/vacancies/applicant/email/{email}")
    public ResponseEntity<List<VacancyDto>> getVacanciesByEmail(@PathVariable String email) {
        return new ResponseEntity<>(vacancyService.findByApplicantEmail(email), HttpStatus.OK);
    }

    @GetMapping("/vacancies/active")
    public ResponseEntity<List<VacancyDto>> getActiveVacancies() {
        return new ResponseEntity<>(vacancyService.findActiveVacancies(), HttpStatus.OK);
    }

    @GetMapping("/vacancies/salary")
    public ResponseEntity<List<VacancyDto>> getVacanciesBySalary(@RequestParam int salaryFrom,
                                                                 @RequestParam int salaryTo) {
        return new ResponseEntity<>(vacancyService.findVacanciesBySalaryRange(salaryFrom, salaryTo), HttpStatus.OK);
    }

    @PostMapping("/apply/{vacancyId}")
    public ResponseEntity<Void> applyForVacancy(@RequestBody String email, @PathVariable int vacancyId) {
        vacancyService.applyForVacancy(email, vacancyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
