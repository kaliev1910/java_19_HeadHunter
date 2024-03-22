package com.example.java_19_headhunter.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class Resume {
    private int id;
    private int applicantId;
    private String name;
    private Integer expectedSalary;
    private int categoryId;
    private boolean isActive;
    private Timestamp createdTime;
    private Timestamp updatedTime;

}
