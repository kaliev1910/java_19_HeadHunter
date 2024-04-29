package com.example.java_19_headhunter.dto.basicDtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class VacancyDto {
    private int id;

    @NotNull
    @Email(message = "Email should be valid")
    private String authorEmail;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name should not be greater than 100 characters")
    private String name;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 500, message = "Description should not be greater than 500 characters")
    private String description;

    private int categoryId;
    private int salary;
    private int expFrom;
    private int expTo;
    private boolean isActive;
    private LocalDate createdDate;
    private LocalDate updateTime;

}