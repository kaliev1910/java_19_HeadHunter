package com.example.java_19_headhunter.api;

import com.example.java_19_headhunter.dto.basicDtos.UserDto;
import com.example.java_19_headhunter.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;


    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserDto userDto) {
        userService.createUser(userDto);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<String> updateProfile(@Valid @RequestBody UserDto userDto)  {
        userService.updateUser(userDto);
        return new ResponseEntity<>("Profile updated successfully", HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/users/email/{email}")
    public ResponseEntity<Optional<UserDto>> getUserByEmail(@PathVariable String email) {
        return new ResponseEntity<>(userService.findByEmail(email), HttpStatus.OK);
    }



    @GetMapping("/users/name/{name}")
    public ResponseEntity<Optional<UserDto>> getUserByName(@PathVariable String name) {
        return new ResponseEntity<>(userService.findByName(name), HttpStatus.OK);
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