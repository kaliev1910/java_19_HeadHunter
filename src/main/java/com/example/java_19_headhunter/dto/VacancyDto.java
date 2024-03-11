package com.example.java_19_headhunter.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class VacancyDto {
    private int id;
    private int authorId;
    private String name;
    private String description;
    private int categoryId;
    private int salary;
    private int expFrom;
    private int expTo;
    private boolean isActive;
    private Timestamp createdDate;
    private Timestamp updateTime;

}
