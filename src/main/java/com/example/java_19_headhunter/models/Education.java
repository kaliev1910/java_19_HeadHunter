package com.example.java_19_headhunter.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Education {
    private int id;
    private int resumeId;
    private String institution;
    private String program;
    private String degree;
    private Date startDate;
    private Date endDate;

}
