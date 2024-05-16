package com.example.java_19_headhunter.controller;

import com.example.java_19_headhunter.dto.basicDtos.UserDto;
import com.example.java_19_headhunter.exeptions.UserNotFoundException;
import com.example.java_19_headhunter.models.User;
import com.example.java_19_headhunter.service.interfaces.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(@RequestParam(defaultValue = "false", required = false) Boolean success,
                                       Model model) {
        if (success.equals(Boolean.TRUE)) {
            return "redirect:/login";
        }


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
        return "redirect:/register?success=true";
    }

    @GetMapping("/login")
    public String login(@RequestParam(defaultValue = "false", required = false) Boolean error, Model model) {
        if (error.equals(Boolean.TRUE)) {
            model.addAttribute("error", "Invalid Username or Password");
        }
        return "/auth/login";
    }

    @GetMapping("forgot_password")
    public String showForgotPasswordForm() {
        return "auth/forgotPassword";
    }

    @PostMapping("forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        try {
            userService.makeResetPasswordLink(request);
            model.addAttribute("message", "We have sent a reset password link to your email. Please check it.");
        } catch (UsernameNotFoundException | UnsupportedEncodingException e) {
            model.addAttribute("error", e.getMessage());
        } catch (MessagingException e) {
            model.addAttribute("error", "Error while sending email");
        }

        return "auth/forgotPassword";
    }

    @GetMapping("reset_password")
    public String showResetPasswordForm(@RequestParam String token, Model model) {
        try {
            userService.getByResetPasswordToken(token);
            model.addAttribute("token", token);
        } catch (UsernameNotFoundException e) {
            model.addAttribute("error", "Invalid token");
        }
        return "auth/resetPassword";
    }

    @PostMapping("reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String passwd = request.getParameter("password");
        try {
            UserDto user = userService.getByResetPasswordToken(token);
            userService.updatePassword(user, passwd);
            model.addAttribute("message", "You have successfully changed password");
        } catch (UsernameNotFoundException e) {
            model.addAttribute("message", "Invalid token");
        }
        return "/auth/message";
    }

}
