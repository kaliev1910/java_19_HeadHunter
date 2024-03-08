package com.example.java_19_headhunter.models;

import lombok.Data;

@Data
public class Vacancy {
    private long id;
    private long userId;
    private String jobTitle;
    private int salary;
    private String description;
    private String requiredExperience;
    private long categoryId;
}
