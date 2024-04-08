package com.example.java_19_headhunter.controller;

import com.example.java_19_headhunter.dto.basicDtos.UserDto;
import com.example.java_19_headhunter.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @PostMapping("/register")
//    public ResponseEntity<String> register(@Valid @RequestBody UserDto userDto) {
//        userService.createUser(userDto);
//        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
//    }

    @GetMapping("update/{email}")
    public String updateProfile(@PathVariable String email){
        return "users/redact";
    }
    @PutMapping("update/{email}")
    public ResponseEntity<String> updateProfile(@RequestBody UserDto userDto) {
        userService.updateUser(userDto);
        return new ResponseEntity<>("Profile updated successfully", HttpStatus.OK);
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<UserDto> users = userService.getUsers();
        model.addAttribute("users", users);
        return "userList";
    }
    @GetMapping("/profile/edit")
    public String showEditProfileForm(Model model, Authentication authentication) {
        // Получаем данные пользователя
        Optional<UserDto> userDto = userService.findByEmail(authentication.getName());
        UserDto user = userDto.orElseThrow();

        // Передаем данные в модель
        model.addAttribute("user", user);

        // Возвращаем имя шаблона для отображения формы редактирования профиля
        return "users/editUser";
    }
    @GetMapping("/users/email/{email}")
    public String getUserByEmail(@PathVariable String email, Model model) {
        Optional<UserDto> userDto = userService.findByEmail(email);
        model.addAttribute("user", userDto.orElse(null));
        return "users/editUser";
    }

    @GetMapping("/users/phoneNumber/{phoneNumber}")
    public String getUserByPhoneNumber(@PathVariable String phoneNumber, Model model) {
        Optional<UserDto> userDto = userService.findByPhoneNumber(phoneNumber);
        model.addAttribute("user", userDto.orElse(null));
        return "userDetails";
    }

    @GetMapping("/users/name/{name}")
    public String getUserByName(@PathVariable String name, Model model) {
        Optional<UserDto> userDto = userService.findByName(name);
        model.addAttribute("user", userDto.orElse(null));
        return "userDetails";
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
