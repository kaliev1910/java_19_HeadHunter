package com.example.java_19_headhunter.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "resumes")
public class Resume {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    @JoinColumn(name = "applicant_email")
    @ManyToOne
    private User applicantEmail;
    private String name;
    private Integer expectedSalary;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categoryId;
    private boolean isActive;
    private Timestamp createdDate;
    private Timestamp updatedTime;



}
