
package com.example.java_19_headhunter.controller.mvc;

import com.example.java_19_headhunter.dto.basicDtos.*;
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
import org.springframework.web.bind.annotation.*;

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
        List<VacancyDto> vacancies = vacancyService.findByUserEmail(authentication.getName());
        model.addAttribute("vacancies", vacancies);
        return "vacancies/vacancies";
    }

    @GetMapping("/vacancies/create")
    public String showCreateVacancyForm(Model model) {
        model.addAttribute("vacancy", new VacancyCreateDto());
        return "vacancies/create_vacancy";
    }

    @PostMapping("/vacancies")
    public String createVacancy(VacancyCreateDto vacancyDto,
                                Authentication authentication,
                                Model model) {
        UserDto employer = userService.findByEmail(authentication.getName()).get();
        int vacancyId = vacancyService.create(vacancyDto, authentication);
        model.addAttribute("message", "Vacancy created successfully");
        return "redirect:/vacancies";
    }


    @GetMapping("/resumes/{id}")
    public String getResumeDetails(@PathVariable int id, Model model) {
        ResumeDto resume = resumeService.findById(id);
        List<ContactInfoDto> contacts = contactInfoService.findByResumeId(id);
        model.addAttribute("resume", resume);
        model.addAttribute("contacts", contacts);
        return "resumes/resume_info";
    }
}