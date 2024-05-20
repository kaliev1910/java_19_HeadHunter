package com.example.java_19_headhunter.controller;

import com.example.java_19_headhunter.dto.basicDtos.*;
import com.example.java_19_headhunter.dto.createDto.ResumeCreateDto;
import com.example.java_19_headhunter.dto.updateDto.ResumeUpdateDto;
import com.example.java_19_headhunter.service.interfaces.*;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private final CategoryService categoryService;


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
                    .educations(educationService.findListByResumeId(resumeDto.getId()))
                    .experiences(experienceService.findListByResumeId(resumeDto.getId()))
                    .contacts(contactInfoService.findListByResumeId(resumeDto.getId()))
                    .build();
            resumes.add(resumeListDto);
        }
        model.addAttribute("resumes", resumes);

        return "resumes/userResumes";
    }

    @GetMapping("/resumes")
    public String getResumes(@RequestParam(name = "filter", required = false, defaultValue = "") String filter,
                             @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = 5) Pageable pageable,
                             Model model) {
        Page<ResumeDto> resumes;


        if (!filter.isEmpty()) {
            // Применить фильтр, если он задан
            resumes = resumeService.getResumesWithPagingByCategories(pageable, Integer.parseInt(filter));
        } else {
            // Получить все резюме без фильтрации
            resumes = resumeService.getResumesWithPaging(pageable);
        }

        Page<ResumeListDto> resumeList = resumes.map(resumeDto -> {
            ResumeListDto resumeListDto = ResumeListDto.builder()
                    .id(resumeDto.getId())
                    .applicantEmail(resumeDto.getApplicantEmail())
                    .name(resumeDto.getName())
                    .expectedSalary(resumeDto.getExpectedSalary())
                    .categoryId(resumeDto.getCategoryId())
                    .isActive(resumeDto.isActive())
                    .createdTime(resumeDto.getCreatedTime())
                    .updatedTime(resumeDto.getUpdatedTime())
                    .educations(educationService.findListByResumeId(resumeDto.getId()))
                    .experiences(experienceService.findListByResumeId(resumeDto.getId()))
                    .contacts(contactInfoService.findListByResumeId(resumeDto.getId()))
                    .build();
            return resumeListDto;
        });

        model.addAttribute("url", "/resumes");
        model.addAttribute("resumes", resumeList);
        return "resumes/resumes";
    }

    @GetMapping("/resumes/create")
    public String showCreateResumeForm(Model model) {
        model.addAttribute("resume", new ResumeCreateDto());
        return "resumes/createResume";
    }

    @PostMapping("/resume/create")
    public String createResume(@RequestBody @ModelAttribute("resumeForm") ResumeCreateDto resumeDto, Authentication authentication, Model model) {

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
        List<EducationDto> educations = educationService.findListByResumeId(resumeId);
        List<ExperienceDto> experiences = experienceService.findListByResumeId(resumeId);
        List<ContactInfoDto> contacts = contactInfoService.findListByResumeId(resumeId);
        model.addAttribute("contacts", contacts);
        model.addAttribute("educations", educations);
        model.addAttribute("experiences", experiences);
        model.addAttribute("resume", resumeService.findById(resumeId));
        model.addAttribute("categories", categoryService.getAllCategories());
        return "resumes/resume_info";
    }

    @GetMapping("/resumes/{resumeId}/edit")
    public String editResume(@PathVariable int resumeId, Model model) {
        ResumeDto resumeDto = resumeService.findById(resumeId);


        ResumeUpdateDto resumeUpdateDto = new ResumeUpdateDto();

        resumeUpdateDto.setId(resumeId);
        resumeUpdateDto.setName(resumeDto.getName());
        resumeUpdateDto.setExpectedSalary(resumeDto.getExpectedSalary());
        resumeUpdateDto.setCategoryId(resumeDto.getCategoryId());

        resumeUpdateDto.setEducation(educationService.findListByResumeId(resumeId));
        resumeUpdateDto.setExperience(experienceService.findListByResumeId(resumeId));
        resumeUpdateDto.setContactInfo(contactInfoService.findListByResumeId(resumeId));

        model.addAttribute("resume", resumeUpdateDto);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "resumes/edit_resume";
    }

    @PostMapping("/resumes/{resumeId}/edit")
    public String editResume(@PathVariable int resumeId, @ModelAttribute("resume") ResumeUpdateDto resumeDto, Authentication authentication, Model model) {

        resumeDto.setId(resumeId);
        resumeService.update(resumeDto, authentication);


        updateEducationInfo(resumeId, resumeDto.getEducation());

        // Обновление информации об опыте работы
        updateExperienceInfo(resumeId, resumeDto.getExperience());

        // Обновление контактной информации
        updateContactInfo(resumeId, resumeDto.getContactInfo());

        return "redirect:/myResumes";
    }

    private void updateEducationInfo(int resumeId, List<EducationDto> educationDto) {
        try {


            if (educationDto == null) {
                educationService.deleteEducationsByResumeId(resumeId);
                return;
            }

            for (EducationDto education : educationDto) {
                education.setResumeId(resumeId);
                if (education.getId() == 0) {
                    educationService.insert(education);
                } else {
                    educationService.update(education);
                }
            }
        } catch (Exception e) {
            log.error("error updating education controller");
        }
    }

    private void updateExperienceInfo(int resumeId, List<ExperienceDto> experienceDto) {
        try {
            // Удаляем существующие записи опыта работы
            experienceService.deleteExperiencesByResumeId(resumeId);

            if (experienceDto != null) {
                // Создаем новые записи опыта работы
                for (ExperienceDto experience : experienceDto) {
                    experience.setResumeId(resumeId);
                    experienceService.insert(experience);
                }
            }
        } catch (Exception e) {
            log.error("Error updating Experience controller", e);
        }
    }

    private void updateContactInfo(int resumeId, List<ContactInfoDto> contactInfoDto) {
        try {
            // Удаляем существующие контактные данные
            contactInfoService.deleteByResumeId(resumeId);

            if (contactInfoDto != null) {
                // Создаем новые контактные данные
                for (ContactInfoDto contactInfo : contactInfoDto) {
                    contactInfo.setResumeId(resumeId);
                    contactInfoService.insert(contactInfo);
                }
            }
        } catch (Exception e) {
            log.error("Error updating contacts controller", e);
        }
    }

}
