package com.example.java_19_headhunter.controller.apiControllers;

import com.example.java_19_headhunter.dto.basicDtos.*;
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
    private final UserService userService;

    @GetMapping("/create")
    public String createResume() {
        return "resumes/resume_add";
    }

    @PostMapping("/create")
    public String createResume(
            ResumeCreateDto resumeDto, EducationDto educationDto, ExperienceDto experienceDto,
            ContactInfoDto contactInfoDto, Authentication authentication, Model model) {


        int resumeId = resumeService.create(resumeDto, authentication);

        experienceDto.setResumeId(resumeId);
        educationDto.setResumeId(resumeId);
        contactInfoDto.setResumeId(resumeId);
        experienceService.insert(experienceDto);
        educationService.insert(educationDto);
        contactInfoService.insert(contactInfoDto);
        UserDto user = userService.findByEmail(authentication.getName()).get();


        List<ResumeDto> resumes = resumeService.findByUserEmail(user.getEmail());
        List<VacancyDto> vacancies = vacancyService.findByUserId(user.getId());
        List<ContactInfoDto> contacts = contactInfoService.findByResumeId(user.getId());

        model.addAttribute("resume", resumes);
        model.addAttribute("vacancies", vacancies);
        model.addAttribute("contacts", contacts);


        return "redirect:resume/email/{}";
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
        return "resumes/resumes";
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
