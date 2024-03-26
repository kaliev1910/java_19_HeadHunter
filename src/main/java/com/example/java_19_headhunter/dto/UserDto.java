package com.example.java_19_headhunter.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private int id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String name;

    @NotNull(message = "Surname cannot be null")
    @Size(min = 2, max = 30, message = "Surname must be between 2 and 30 characters")
    private String surname;

    @NotNull(message = "Age cannot be null")
    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 150, message = "Age should not be greater than 150")
    private byte age;

    @NotNull
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    private String avatar;

    @NotNull(message = "Account type cannot be null")
    @Pattern(regexp = "applicant|employer", message = "Account type must be either 'applicant' or 'employer'")
    private String accountType;
    private boolean enabled;
}



