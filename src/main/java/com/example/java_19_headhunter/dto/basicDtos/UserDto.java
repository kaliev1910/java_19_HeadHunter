package com.example.java_19_headhunter.dto.basicDtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Max(value = 120, message = "Age should not be greater than 120")
    private byte age;

    @NotNull
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 4, max = 24,
            message = "Length of password must be >= 4 and <= 24")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$",
            message = "Password should contain at least one uppercase letter, one number")
    private String password;

    private String avatar;

    @NotNull(message = "Account type cannot be null")
    @Pattern(regexp = "APPLICANT|EMPLOYER", message = "Account type must be either 'APPLICANT' or 'EMPLOYER'")
    private String accountType;

    private boolean enabled;

}



