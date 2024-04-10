package com.example.java_19_headhunter.controller.mvc;

import com.example.java_19_headhunter.dto.basicDtos.*;
import com.example.java_19_headhunter.dto.createDto.ResumeCreateDto;
import com.example.java_19_headhunter.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('APPLICANT')")
public class ApplicantController {
    private final ContactInfoService contactInfoService;
    private final VacancyService vacancyService;
    private final UserService userService;
    private final ResumeService resumeService;
    private final ExperienceService experienceService;
    private final EducationService educationService;


    @GetMapping("/myResumes")
    public String getResumesForm(Model model, Authentication authentication) {
        return "resumes/userResumes";
    }

    @GetMapping("/resumes/create")
    public String showCreateResumeForm(Model model) {
        return "resumes/resume_add";
    }

    @GetMapping("/resumes/{resumeId}")
    public String showResumeInfo(@PathVariable int resumeId, Model model) {

        model.addAttribute("contacts", contactInfoService.findByResumeId(resumeId));
        model.addAttribute("educations",educationService.findByResumeId(resumeId));
        model.addAttribute("experiences", experienceService.findByResumeId(resumeId));
        model.addAttribute("resume", resumeService.findById(resumeId));
        return "resumes/resume_info";
    }


    @PostMapping("/resumes")
    public String createResume(ResumeCreateDto resumeDto,
                               EducationDto educationDto,
                               ExperienceDto experienceDto,
                               Authentication authentication,
                               ContactInfoDto contactInfoDto,
                               Model model) {

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

        return "resumes/userResumes";
    }
}