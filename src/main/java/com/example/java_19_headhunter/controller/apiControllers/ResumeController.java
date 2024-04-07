package com.example.java_19_headhunter.controller.apiControllers;

import com.example.java_19_headhunter.dto.ContactInfoDto;
import com.example.java_19_headhunter.dto.EducationDto;
import com.example.java_19_headhunter.dto.ExperienceDto;
import com.example.java_19_headhunter.dto.ResumeDto;
import com.example.java_19_headhunter.dto.createDto.ResumeCreateDto;
import com.example.java_19_headhunter.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/resume")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;
    private final EducationService educationService;
    private final ExperienceService experienceService;
    private final ContactInfoService contactInfoService;
    private final VacancyService vacancyService;
    private final MessageService messageService;

    @PostMapping("/create")
    public String createResume(
            @Validated @RequestBody ResumeCreateDto resumeDto,
            EducationDto educationDto, ExperienceDto experienceDto,
            Authentication authentication, ContactInfoDto contactInfoDto, Model model) {

       int resumeId=  resumeService.create(resumeDto, authentication);

        experienceDto.setResumeId(resumeId);
        educationDto.setResumeId(resumeId);
        contactInfoDto.setResumeId(resumeId);
        experienceService.insert(experienceDto);
        educationService.insert(educationDto);
        contactInfoService.insert(contactInfoDto);
        model.addAttribute("message", "Resume created successfully");
        return "redirect:resume/create";
    }

    @PutMapping("/update")
    public String updateResume(@Validated @RequestBody ResumeCreateDto resumeDto, Authentication authentication, Model model) {
        resumeService.update(resumeDto, authentication);
        model.addAttribute("message", "Resume updated successfully");
        return "redirect:/resume";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteResume(@PathVariable int id, Model model) {
        resumeService.deleteById(id);
        model.addAttribute("message", "Resume deleted successfully");
        return "redirect:/resume";
    }

    @GetMapping("/category/{id}")
    public String getResumesByCategory(@PathVariable int id, Model model) {
        List<ResumeDto> resumes = resumeService.findByCategory(id);
        model.addAttribute("resumes", resumes);
        return "resume-list";
    }

    @GetMapping
    public String getAllResumes(Model model) {
        List<ResumeDto> resumes = resumeService.getAll();
        model.addAttribute("resumes", resumes);
        return "resume-list";
    }

    @GetMapping("/email/{email}")
    public String getResumesByEmail(@PathVariable String email, Model model) {
        List<ResumeDto> resumes = resumeService.findByUserEmail(email);
        model.addAttribute("resumes", resumes);
        return "resume-list";
    }

    @GetMapping("/{id}")
    public String getResumeById(Model model, @PathVariable int id) {
        ResumeDto resume = resumeService.findById(id);
        List<ContactInfoDto> contacts = contactInfoService.findByResumeId(id);
        List<ExperienceDto> experiences = experienceService.findByResumeId(id);
        List<EducationDto> educations = educationService.findByResumeId(id);

        model.addAttribute("resume", resume);
        model.addAttribute("contacts", contacts);
        model.addAttribute("educations", educations);
        model.addAttribute("experiences", experiences);

        return "/resumes/resume_info";
    }
}
