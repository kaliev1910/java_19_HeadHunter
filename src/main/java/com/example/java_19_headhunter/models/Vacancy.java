package com.example.java_19_headhunter.models;

import lombok.Data;

@Data
public class Vacancy {
    private long id;
    private String name;
    private long userId;
    private int salary;
    private String description;
    private String requiredExperience;
    private long categoryId;
}
