package com.example.java_19_headhunter.controller.mvc;

import com.example.java_19_headhunter.dto.basicDtos.ResumeDto;
import com.example.java_19_headhunter.dto.basicDtos.UserDto;
import com.example.java_19_headhunter.dto.basicDtos.VacancyDto;
import com.example.java_19_headhunter.service.ResumeService;
import com.example.java_19_headhunter.service.UserService;
import com.example.java_19_headhunter.service.VacancyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('APPLICANT')")
public class MainController {

    private final UserService userService;
    private final ResumeService resumeService;
    private final VacancyService vacancyService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());

        return "auth/register";
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String registerUser(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            return "auth/register";
        }

        if (userService.findByEmail(userDto.getEmail()).isPresent()) {
            bindingResult.rejectValue("email", "error.user", "There is already a user registered with the provided email");
            return "auth/register";
        }

        userService.createUser(userDto);
        return "redirect:/register?success";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginDto", new UserDto());
        return "/auth/login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginDto") UserDto userDto,
                        BindingResult bindingResult,
                        Model model) {
        if (bindingResult.hasErrors()) {
            return "/auth/login";
        }
        return "redirect:/home";
    }

    @GetMapping("/profile")
    public String getUserProfile(Model model, Authentication authentication) {
        UserDto user = userService.findByEmail(authentication.getName()).get();

        List<ResumeDto> resumes = resumeService.findByUserEmail(user.getEmail());
        List<VacancyDto> vacancies = vacancyService.findByUserId(user.getId());

        int resumeId = resumes.get(0).getId();

        model.addAttribute("user", user);
        model.addAttribute("resume", resumes);
        model.addAttribute("vacancies", vacancies);

        return "users/index"; // Это имя вашего шаблона
    }

    @GetMapping("/{email}")
    public String showEditUser(@PathVariable String email, Model model, Authentication authentication) {
        model.addAttribute("user", userService.findByEmail(authentication.getName()).get());
        return "users/editUser";
    }

    @PostMapping("/{email}")
    public String updateUser(String email, @Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult, Model model) {
        userService.updateUser(userDto);
        return "redirect:/profile";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "auth/login";
    }
}
