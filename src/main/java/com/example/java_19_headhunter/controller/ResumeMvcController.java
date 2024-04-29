package com.example.java_19_headhunter.controller;

import com.example.java_19_headhunter.dto.basicDtos.*;
import com.example.java_19_headhunter.dto.createDto.ResumeCreateDto;
import com.example.java_19_headhunter.service.interfaces.*;
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
        List<ResumeDto> resumesDtos = resumeService.findByUserEmail(user.getEmail());
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
        return "resumes/userResumes";
    }

    @GetMapping("resumes")
    public String gteResumesForm(@RequestParam(name = "page") Integer page, Model model) {
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
        List<EducationDto> educations = educationService.findByResumeId(resumeId);
        List<ExperienceDto> experiences = experienceService.findByResumeId(resumeId);
        List<ContactInfoDto> contacts = contactInfoService.findByResumeId(resumeId);
        model.addAttribute("contacts", contacts);
        model.addAttribute("educations", educations);
        model.addAttribute("experiences", experiences);
        model.addAttribute("resume", resumeService.findById(resumeId));
        return "resumes/resume_info";
    }

    @GetMapping("/resumes/{resumeId}/edit")
    public String editResume(@PathVariable int resumeId, Model model) {
        List<EducationDto> educations = educationService.findByResumeId(resumeId);
        List<ExperienceDto> experiences = experienceService.findByResumeId(resumeId);
        List<ContactInfoDto> contacts = contactInfoService.findByResumeId(resumeId);
        int eduIndex= 0;
        model.addAttribute("eduIndex", eduIndex);
        model.addAttribute("contacts", contacts);
        model.addAttribute("educations", educations);
        model.addAttribute("experiences", experiences);
        model.addAttribute("resume", resumeService.findById(resumeId));
        return "resumes/edit_resume";
    }

    @PostMapping("/resume/{resumeId}/edit")
    public String editResume(@PathVariable int resumeId, @Valid @RequestBody ResumeCreateDto resumeDto, Authentication authentication, Model model) {

        resumeService.update(resumeDto, authentication);

        List<EducationDto> educationDto = resumeDto.getEducation();
        List<ExperienceDto> experienceDto = resumeDto.getExperience();
        List<ContactInfoDto> contactInfoDto = resumeDto.getContactInfo();

        // функционал сделан так: если пользователь просто изменил образование используется Update.
        // если добавил
        if (educationDto != null) {
            for (EducationDto education : educationDto) {
                education.setResumeId(resumeId);
                if (education.getResumeId() == resumeId) {
                    educationService.insert(education);
                    log.info("added education for resume {}", education.getResumeId());
                } else {
                    educationService.update(education);
                    log.info("education id {} has updated for resume id {}", education.getId(), resumeId);
                }

            }
            log.info("education = {}", educationDto);
        } else {
            educationService.deleteByResumeId(resumeId);
        }

        if (experienceDto != null) {
            for (ExperienceDto experience : experienceDto) {
                experience.setResumeId(resumeId);
                if (experience.getResumeId() == resumeId) {
                    experienceService.insert(experience);
                    log.info("added experience for resume {}", experience.getResumeId());
                } else {
                    experienceService.update(experience);
                    log.info("experience id {} has updated", experience.getId());
                }

            }
        }
        else {
            experienceService.deleteByResumeId(resumeId);
        }

        if (contactInfoDto != null) {
            for (ContactInfoDto contactInfo : contactInfoDto) {
                contactInfo.setResumeId(resumeId);
                if (contactInfo.getResumeId() == resumeId) {
                    contactInfoService.insert(contactInfo);
                    log.info("added contacts for resume {}", contactInfo.getResumeId());
                }
                else {
                    contactInfoService.update(contactInfo);
                    log.info("contact id {} has updated", contactInfo.getResumeId());
                }
            }
        }
        else {
            contactInfoService.deleteByResumeId(resumeId);
        }
        log.info("resume edited {}", resumeDto.getName());

        model.addAttribute("message", "Resume edited successfully");
        return "redirect:/myResumes";
    }


}
