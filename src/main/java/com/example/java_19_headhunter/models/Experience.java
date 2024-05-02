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
@Table(name = "experiences")
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JoinColumn(name = "resume_id")
    @ManyToOne
    private Resume resumeId;
    private String companyName;
    private String position;
    private String responsibilities;
    private int years;
    private Date startDate;
    private Date endDate;
}
