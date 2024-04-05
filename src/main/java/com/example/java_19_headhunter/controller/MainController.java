package com.example.java_19_headhunter.controller;

import com.example.java_19_headhunter.dto.*;
import com.example.java_19_headhunter.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    private final ResumeService resumeService;
    private final EducationService educationService;
    private final ExperienceService experienceService;
    private final ContactInfoService contactInfoService;
    private final VacancyService vacancyService;
    private final MessageService messageService;

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

        //TODO сюда логику аутентификации

        return "redirect:/home";
    }


    @GetMapping("/profile")
    public String getUserProfile(Model model) {
        // Здесь предполагается, что у вас есть сервис, который возвращает информацию о пользователе
        UserDto user = getUserInfoFromService();
        List<ResumeDto> resumes = resumeService.findByUserEmail(user.getEmail());

        int resumeId= resumes.get(0).getId();

        List<ExperienceDto> experienceDtos = experienceService.findByResumeId(resumeId);
        List<EducationDto> educationDtos = educationService.findByResumeId(resumeId);
        List<ContactInfoDto> contactInfoDtos = contactInfoService.findByResumeId(resumeId);
        List<VacancyDto> vacancyDtos = vacancyService.findByApplicantEmail(user.getEmail());
        // Пример заполнения данных для шаблона
        model.addAttribute("user", user);
        model.addAttribute("resume", resumes);
        model.addAttribute("contacts", contactInfoDtos);
        model.addAttribute("educations", educationDtos);
        model.addAttribute("experiences", experienceDtos);
        model.addAttribute("vacancies", vacancyDtos);

        return "/profile/profile"; // Это имя вашего шаблона
    }

    // Пример метода, который возвращает информацию о пользователе из вашего сервиса
    private UserDto getUserInfoFromService() {
        // Здесь можно использовать ваш сервис для получения информации о пользователе из базы данных или другого источника
        // Пример данных о пользователе
        UserDto user = userService.getUsers().get(1);
        // Заполните другие поля пользователя, если необходимо

        // Возвращаем информацию о пользователе
        return user;
    }
}
