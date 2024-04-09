package com.example.java_19_headhunter.controller.mvc;

import com.example.java_19_headhunter.dto.basicDtos.*;
import com.example.java_19_headhunter.dto.createDto.ResumeCreateDto;
import com.example.java_19_headhunter.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ApplicantController {
    private final ContactInfoService contactInfoService;
    private final VacancyService vacancyService;
    private final MessageService messageService;
    private final UserService userService;
    private final ResumeService resumeService;
    private final ExperienceService experienceService;
    private final EducationService educationService;

    @GetMapping("resumes")
    public String getResumesForm(Model model, Authentication authentication) {

        return "resumes/resumes";
    }

    @GetMapping("create")
    public String createResume() {
        return "resumes/resume_add";
    }

    @PostMapping("/create")
    public String createResume(
            @Validated @RequestBody ResumeCreateDto resumeDto,
            EducationDto educationDto, ExperienceDto experienceDto,
            Authentication authentication, ContactInfoDto contactInfoDto, Model model) {


        int resumeId = resumeService.create(resumeDto, authentication);


        experienceDto.setResumeId(resumeId);
        educationDto.setResumeId(resumeId);
        contactInfoDto.setResumeId(resumeId);
        experienceService.insert(experienceDto);
        educationService.insert(educationDto);
        contactInfoService.insert(contactInfoDto);
        model.addAttribute("message", "Resume created successfully");

        UserDto user = userService.findByEmail(authentication.getName()).get();


        List<ResumeDto> resumes = resumeService.findByUserEmail(user.getEmail());
        List<VacancyDto> vacancies = vacancyService.findByUserId(user.getId());
        List<ContactInfoDto> contacts = contactInfoService.findByResumeId(user.getId());

        model.addAttribute("resume", resumes);
        model.addAttribute("vacancies", vacancies);
        model.addAttribute("contacts", contacts);


        return "resumes/resumes";
    }
}
