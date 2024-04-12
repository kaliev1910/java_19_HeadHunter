package com.example.java_19_headhunter.models;

import lombok.*;

import java.sql.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Experience {
    private int id;
    private int resumeId;
    private String companyName;
    private String position;
    private String responsibilities;
    private int years;
    private Date startDate;
    private Date endDate;
}
