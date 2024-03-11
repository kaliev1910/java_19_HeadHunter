package com.example.java_19_headhunter.models;


import lombok.Builder;

import java.sql.Date;

@Builder
public class Education {
    private int id;
    private int resumeId;
    private String institution;
    private String program;
    private String degree;
    private Date startDate;
    private Date endDate;

}
