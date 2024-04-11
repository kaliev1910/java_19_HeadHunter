package com.example.java_19_headhunter.controller.mvc;

import com.example.java_19_headhunter.dto.basicDtos.*;
import com.example.java_19_headhunter.dto.createDto.ResumeCreateDto;
import com.example.java_19_headhunter.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ResumeMvcController {

    private final ResumeService resumeService;
    private final EducationService educationService;
    private final ExperienceService experienceService;
    private final UserService userService;
    private final ContactInfoService contactInfoService;


    @GetMapping("/myResumes")
    public String getResumesForm(Model model, Authentication authentication) {
        UserDto user = userService.findByEmail(authentication.getName()).get();

//TODO переделать чтобы показывал не резюме а resumeListDto и модель получала контакты только этого резюме
        //TODO         List<ResumeListDto> resumes; contacts = resumes.getContacts()

        List<ResumeDto> resumes = resumeService.findByUserEmail(user.getEmail());
        List<ContactInfoDto> contacts = contactInfoService.findByResumeId(user.getId());

        model.addAttribute("resume", resumes);
        model.addAttribute("contacts", contacts);
        return "resumes/userResumes";
    }

    @GetMapping("/resumes/create")
    public String showCreateResumeForm(Model model) {
        model.addAttribute("resume", new ResumeCreateDto());
        return "resumes/resume_add";
    }

    @PostMapping("/resume/create")
    public String createResume(ResumeCreateDto resumeDto, Authentication authentication, Model model) {

        int resumeId = resumeService.create(resumeDto, authentication);


        List<EducationDto> educationDto = resumeDto.getEducation();
        List<ExperienceDto> experienceDto = resumeDto.getExperience();
        List<ContactInfoDto> contactInfoDto = resumeDto.getContactInfo();
        if (educationDto != null) {
            for (EducationDto education : educationDto) {
                education.setResumeId(resumeId);
                educationService.insert(education);
                log.info("added education for resume {}", education.getResumeId());
            }

        }
        if (experienceDto != null) {
            for (ExperienceDto experience : experienceDto) {
                experience.setResumeId(resumeId);
                experienceService.insert(experience);
            }
        }


        if (contactInfoDto != null) {
            for (ContactInfoDto contactInfo : contactInfoDto) {
                contactInfo.setResumeId(resumeId);
                contactInfoService.insert(contactInfo);
            }
        }

        model.addAttribute("message", "Resume created successfully");
        return "redirect:/myResumes";
    }

    @GetMapping("/resume/{resumeId}")
    public String showResumeInfo(@PathVariable int resumeId, Model model) {
        model.addAttribute("contacts", contactInfoService.findByResumeId(resumeId));
        model.addAttribute("educations", educationService.findByResumeId(resumeId));
        model.addAttribute("experiences", experienceService.findByResumeId(resumeId));
        model.addAttribute("resume", resumeService.findById(resumeId));
        return "resumes/resume_info";
    }
}
