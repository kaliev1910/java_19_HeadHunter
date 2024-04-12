package com.example.java_19_headhunter.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Vacancy {
    private int id;
    private String authorEmail;
    private String name;
    private String description;
    private int categoryId;
    private int salary;
    private int expFrom;
    private int expTo;
    private boolean isActive;
    private LocalDate createdDate;
    private LocalDate updateTime;

}
