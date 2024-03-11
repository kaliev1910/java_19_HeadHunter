package com.example.java_19_headhunter.models;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
public class Resume {
    private long id;
    private long applicantId;
    private String name;
    private Integer expectedSalary;
    private int experienceId;
    private int educationId;
    private  long contactId;
    private LocalDate createdTime;
    private LocalDate updatedTime;

}
