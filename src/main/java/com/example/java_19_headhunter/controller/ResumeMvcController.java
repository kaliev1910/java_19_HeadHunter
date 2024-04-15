package com.example.java_19_headhunter.controller;

import com.example.java_19_headhunter.dto.basicDtos.*;
import com.example.java_19_headhunter.dto.createDto.ResumeCreateDto;
import com.example.java_19_headhunter.service.*;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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
    public String getMyResumesForm(Model model, Authentication authentication) {

        UserDto user = userService.findByEmail(authentication.getName()).get();

//TODO переделать чтобы показывал не резюме а resumeListDto и контакты в шаблоне получала контакты этого резюме #list resume.contacts as contact

        List<ResumeDto> resumes = resumeService.findByUserEmail(user.getEmail());
        List<ContactInfoDto> contacts = contactInfoService.findByResumeId(user.getId());

        model.addAttribute("resume", resumes);
        model.addAttribute("contacts", contacts);
        return "resumes/userResumes";
    }

    @GetMapping("/resumes")
    public String getResumesForm(@RequestParam(name = "page") Integer page, Model model) {

        List<ResumeDto> resumesDtos = resumeService.getResumesWithPaging(page, 6);
        List<ResumeListDto> resumes = new ArrayList<>();

        for (ResumeDto resumeDto : resumesDtos) {
            ResumeListDto resumeListDto = ResumeListDto.builder()
                    .id(resumeDto.getId())
                    .applicantEmail(resumeDto.getApplicantEmail())
                    .name(resumeDto.getName())
                    .expectedSalary(resumeDto.getExpectedSalary())
                    .categoryId(resumeDto.getCategoryId())
                    .isActive(resumeDto.isActive())
                    .createdTime(resumeDto.getCreatedTime())
                    .updatedTime(resumeDto.getUpdatedTime())
                    .educations(educationService.findByResumeId(resumeDto.getId()))
                    .experiences(experienceService.findByResumeId(resumeDto.getId()))
                    .contacts(contactInfoService.findByResumeId(resumeDto.getId()))
                    .build();

            resumes.add(resumeListDto);
        }
        model.addAttribute("resumes", resumes);
        return "resumes/resumes";
    }

    @GetMapping("/resumes/create")
    public String showCreateResumeForm(Model model) {
        model.addAttribute("resume", new ResumeCreateDto());
        return "resumes/createResume";
    }

    @PostMapping("/resume/create")
    public String createResume(@Valid @RequestBody ResumeCreateDto resumeDto, Authentication authentication, Model model) {

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
            log.info("education = {}", educationDto);
        }
        if (experienceDto != null) {
            for (ExperienceDto experience : experienceDto) {
                experience.setResumeId(resumeId);
                experienceService.insert(experience);
                log.info("added experience for resume {}", experience.getResumeId());
            }
        }


        if (contactInfoDto != null) {
            for (ContactInfoDto contactInfo : contactInfoDto) {
                contactInfo.setResumeId(resumeId);
                contactInfoService.insert(contactInfo);
                log.info("added contacts for resume {}", contactInfo.getResumeId());
            }
        }
        log.info("resume added {}", resumeDto.getName());

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

    @GetMapping("/resumes/{id}")
    public String getResumeDetails(@PathVariable int id, Model model) {
        ResumeDto resume = resumeService.findById(id);
        List<ContactInfoDto> contacts = contactInfoService.findByResumeId(id);
        model.addAttribute("resume", resume);
        model.addAttribute("contacts", contacts);
        return "resumes/resume_info";
    }
}
