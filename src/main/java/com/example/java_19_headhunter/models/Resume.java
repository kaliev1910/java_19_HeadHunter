package com.example.java_19_headhunter.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class Resume {
    private long id;
    private long applicantId;
    private String name;
    private Integer expectedSalary;
    private int categoryId;
    private boolean isActive;
    private LocalDate createdTime;
    private LocalDate updatedTime;

}
