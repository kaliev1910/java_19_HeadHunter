package com.example.java_19_headhunter.controller;

import com.example.java_19_headhunter.dto.UserDto;
import com.example.java_19_headhunter.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "auth/register";
    }

    @PostMapping("/register")
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
}
