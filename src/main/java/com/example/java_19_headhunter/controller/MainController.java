package com.example.java_19_headhunter.controller;

import com.example.java_19_headhunter.dto.basicDtos.*;
import com.example.java_19_headhunter.enums.AccountType;
import com.example.java_19_headhunter.models.UserResponse;
import com.example.java_19_headhunter.service.impl.UserImageService;
import com.example.java_19_headhunter.service.interfaces.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    private final ResumeService resumeService;
    private final VacancyService vacancyService;
    private final UserImageService userImageService;
    private final ContactInfoService contactInfoService;
    private final ResponseService responseService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        return "auth/register";
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String registerUser(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "auth/register";
        }
        if (userService.findByEmail(userDto.getEmail()).isPresent()) {
            bindingResult.rejectValue("email", "error.user", "There is already a user registered with the provided email");
            model.addAttribute("userDto", userDto);
            return "auth/register";
        }

        userService.createUser(userDto);
        return "redirect:/register?success";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "/auth/login";
    }
 @GetMapping("/error")
    public String showAccessDeniedPage() {
        return "/errors/accessDenied";
    }

    @GetMapping("/")
    public String showMainPage() {
        return "redirect:/vacancies?page=1";
    }

    @GetMapping("/chat")
    public String showChatPage(Authentication authentication, Model model) {
        model.addAttribute("username", authentication.getName());
        return "chat/chat";
    }


    @GetMapping("/profile")
    public String getUserProfile(Model model, Authentication authentication) {
        UserDto user = userService.findByEmail(authentication.getName()).get();
        Long userId = (long) user.getId();
        List<ResumeDto> resumes = resumeService.findByUserEmail(user.getEmail());
        List<VacancyDto> vacancies = vacancyService.findByUserId(user.getId());
        if (user.getAccountType().equals(AccountType.APPLICANT.getValue())) {
            List<UserResponse> responses = responseService.getUserResponses(user.getEmail());
            model.addAttribute("resumes", resumes);
            model.addAttribute("responses", responses);
        }
        if (user.getAccountType().equals(AccountType.EMPLOYER.getValue())) {
            String userAccType = AccountType.EMPLOYER.getValue();
            List<UserResponse> responses = responseService.getUserResponses(user.getEmail());
            model.addAttribute("vacancies", vacancies);
            model.addAttribute("responses", responses);
        }
        model.addAttribute("user", user);


        return "users/index"; // Это имя вашего шаблона
    }


    @GetMapping("/{email}/edit")
    public String showEditUser(@PathVariable String email, Model model, Authentication authentication) {
        model.addAttribute("user", userService.findByEmail(authentication.getName()).get());
        return "users/editUser";
    }

    @PostMapping("/{email}/edit")
    public String updateUser(@Valid UserDto userDto) {
        userService.updateUser(userDto);
        return "redirect:/profile";
    }


    @PostMapping("/upload")
    public String uploadImage(UserImageDto userImageDto) {
        userImageService.uploadImage(userImageDto);
        return "redirect:profile";
    }


}
