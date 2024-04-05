package com.example.java_19_headhunter.controller.apiControllers;

import com.example.java_19_headhunter.dto.UserDto;
import com.example.java_19_headhunter.dto.ResumeDto;
import com.example.java_19_headhunter.dto.VacancyDto;
import com.example.java_19_headhunter.dto.createDto.ResumeCreateDto;
import com.example.java_19_headhunter.service.ResumeService;
import com.example.java_19_headhunter.service.impl.UserServiceImpl;
import com.example.java_19_headhunter.service.impl.VacancyServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/resume")
@RequiredArgsConstructor
@PreAuthorize("hasRole('APPLICANT')")
public class ResumeController {
    private final UserServiceImpl userService;
    private final ResumeService resumeService;
    private final VacancyServiceImpl vacancyService;

    @PostMapping("/resume")
    public String createResume(@Valid @ModelAttribute("resumeDto") ResumeDto resumeDto, Model model, Authentication authentication) {
        resumeService.create(resumeDto,authentication);
        model.addAttribute("message", "Resume created successfully");
        return "redirect:/resume/resumes";
    }

    @PutMapping("/resume/{id}")
    public String updateResume(@PathVariable int id, @Valid @ModelAttribute("resumeDto") ResumeCreateDto resumeDto, Model model, Authentication authentication) {
        resumeService.update(resumeDto, authentication);
        model.addAttribute("message", "Resume updated successfully");
        return "redirect:/resume/resumes";
    }

    @DeleteMapping("/resume/{id}")
    public String deleteResume(@PathVariable int id, Model model) {
        resumeService.deleteById(id);
        model.addAttribute("message", "Resume deleted successfully");
        return "redirect:/resume/resumes";
    }

    @GetMapping("/resumes/category/{id}")
    public String getResumesByCategory(@PathVariable int id, Model model) {
        List<ResumeDto> resumes = resumeService.findByCategory(id);
        model.addAttribute("resumes", resumes);
        return "resume-list";
    }

    @GetMapping("/resumes")
    public String getAllResumes(Model model) {
        List<ResumeDto> resumes = resumeService.getAll();
        model.addAttribute("resumes", resumes);
        return "resume-list";
    }

    @GetMapping("/resumes/email/{email}")
    public String getResumesByEmail(@PathVariable String email, Model model) {
        List<ResumeDto> resumes = resumeService.findByUserEmail(email);
        model.addAttribute("resumes", resumes);
        return "resume-list";
    }

    @GetMapping("/resumes/{id}")
    public String getResumesById(@PathVariable int id, Model model) {
        ResumeDto resume = resumeService.findById(id);
        model.addAttribute("resume", resume);
        return "resume-details";
    }

    @GetMapping("/resume/{id}")
    public String getResumeById(@PathVariable int id, Model model) {
        ResumeDto resume = resumeService.findById(id);
        model.addAttribute("resume", resume);
        return "resume-details";
    }
}
