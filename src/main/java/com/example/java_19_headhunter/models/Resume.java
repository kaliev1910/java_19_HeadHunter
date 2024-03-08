package com.example.java_19_headhunter.models;

import lombok.Data;

@Data
public class Resume {
    private long id;
    private long userId;
    private String position;
    private Integer expectedSalary;
    private String experience;
    private String education;
    private  long contactId;
}
