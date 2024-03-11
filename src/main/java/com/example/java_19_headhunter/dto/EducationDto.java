package com.example.java_19_headhunter.dto;


import lombok.Builder;

import java.sql.Date;

@Builder
public class EducationDto {
    private int id;
    private int resumeId;
    private String institution;
    private String program;
    private String degree;
    private Date startDate;
    private Date endDate;

}
