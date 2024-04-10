package com.example.java_19_headhunter.dto.basicDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class CategoryDto {

    private int id;
    private int parentId;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 50, message = "Name should not be greater than 50 characters")
    private String name;
}