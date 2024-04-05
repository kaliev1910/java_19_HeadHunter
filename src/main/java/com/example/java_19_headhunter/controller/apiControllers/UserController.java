package com.example.java_19_headhunter.controller.apiControllers;

import com.example.java_19_headhunter.dto.UserDto;
import com.example.java_19_headhunter.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("userDto") UserDto userDto, Model model) {
        userService.createUser(userDto);
        model.addAttribute("message", "User registered successfully");
        return "redirect:/user/users";
    }

    @PutMapping("")
    public String updateProfile(@Valid @ModelAttribute("userDto") UserDto userDto, Model model) {
        userService.updateUser(userDto);
        model.addAttribute("message", "Profile updated successfully");
        return "redirect:/user/users";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<UserDto> users = userService.getUsers();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/users/email/{email}")
    public String getUserByEmail(@PathVariable String email, Model model) {
        Optional<UserDto> user = userService.findByEmail(email);
        model.addAttribute("user", user.orElse(null));
        return "user-details";
    }

    @GetMapping("/users/phoneNumber/{phoneNumber}")
    public String getUserByPhoneNumber(@PathVariable String phoneNumber, Model model) {
        Optional<UserDto> user = userService.findByPhoneNumber(phoneNumber);
        model.addAttribute("user", user.orElse(null));
        return "user-details";
    }

    @GetMapping("/users/name/{name}")
    public String getUserByName(@PathVariable String name, Model model) {
        Optional<UserDto> user = userService.findByName(name);
        model.addAttribute("user", user.orElse(null));
        return "user-details";
    }

    @GetMapping("/users/exists/{email}")
    public ResponseEntity<Boolean> userExists(@PathVariable String email) {
        return new ResponseEntity<>(userService.userExists(email), HttpStatus.OK);
    }

    @GetMapping("/users/type")
    public ResponseEntity<Boolean> getUserType(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.getUserType(userDto), HttpStatus.OK);
    }
}
