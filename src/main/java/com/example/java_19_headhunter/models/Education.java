package com.example.java_19_headhunter.models;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "educations")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JoinColumn(name = "resume_id")
    @ManyToOne
    private Resume resumeId;
    private String institution;
    private String program;
    private String degree;
    private Date startDate;
    private Date endDate;

}
