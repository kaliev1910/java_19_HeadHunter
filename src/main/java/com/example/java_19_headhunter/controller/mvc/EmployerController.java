package com.example.java_19_headhunter.controller.mvc;

import com.example.java_19_headhunter.dto.basicDtos.ContactInfoDto;
import com.example.java_19_headhunter.dto.basicDtos.ResumeDto;
import com.example.java_19_headhunter.dto.basicDtos.UserDto;
import com.example.java_19_headhunter.dto.basicDtos.VacancyDto;
import com.example.java_19_headhunter.dto.createDto.VacancyCreateDto;
import com.example.java_19_headhunter.service.ContactInfoService;
import com.example.java_19_headhunter.service.ResumeService;
import com.example.java_19_headhunter.service.UserService;
import com.example.java_19_headhunter.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('EMPLOYER')")
public class EmployerController {
    private final UserService userService;
    private final VacancyService vacancyService;
    private final ResumeService resumeService;
    private final ContactInfoService contactInfoService;



    @GetMapping("/vacancies")
    public String getVacancies(Model model, Authentication authentication) {
        UserDto employer = userService.findByEmail(authentication.getName()).get();
        List<VacancyDto> vacancies = vacancyService.findByUserId(employer.getId());
        model.addAttribute("vacancies", vacancies);
        return "vacancies/vacancies";
    }

    @GetMapping("/vacancies/create")
    public String showCreateVacancyForm(Model model) {
        model.addAttribute("vacancy", new VacancyCreateDto());
        return "vacancies/create_vacancy";
    }

//    @PostMapping("/vacancies/create")
//    public String createVacancy(@ModelAttribute VacancyCreateDto vacancyDto, Authentication authentication, Model model) {
//        UserDto employer = userService.findByEmail(authentication.getName()).get();
//        vacancyDto.set(employer.getId());
//        vacancyService.create(vacancyDto);
//        model.addAttribute("message", "Vacancy created successfully");
//        return "redirect:/employer/vacancies";
//    }

//    @GetMapping("/resumes")
//    public String getResumes(Model model, Authentication authentication) {
//        UserDto employer = userService.findByEmail(authentication.getName());
//        List<ResumeDto> resumes = resumeService.getAllResumes();
//        model.addAttribute("resumes", resumes);
//        return "employer/resumes";
//    }
//
//    @GetMapping("/resumes/{id}")
//    public String getResumeDetails(@PathVariable Long id, Model model) {
//        ResumeDto resume = resumeService.getResumeById(id);
//        List<ContactInfoDto> contacts = contactInfoService.findByResumeId(id);
//        model.addAttribute("resume", resume);
//        model.addAttribute("contacts", contacts);
//        return "employer/resume_details";
//    }
}
