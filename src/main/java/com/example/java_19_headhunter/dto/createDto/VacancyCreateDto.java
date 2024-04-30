package com.example.java_19_headhunter.dto.createDto;

import com.example.java_19_headhunter.dto.basicDtos.ContactInfoDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class VacancyCreateDto {
    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name should not be greater than 100 characters")
    private String name;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 500, message = "Description should not be greater than 500 characters")
    private String description;

    private int categoryId;
    private int salary;
    private int expFrom;
    private Timestamp createdDate;
    private Timestamp updateTime;
    private List<ContactInfoDto> contactInfo;

}
