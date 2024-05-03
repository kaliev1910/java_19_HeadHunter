package com.example.java_19_headhunter.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "resumes")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JoinColumn(name = "applicant_email")
    @ManyToOne
    private User applicantEmail;
    private String name;
    @Column(name = "expected_salary")
    private Integer expectedSalary;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categoryId;
    @Column(name = "is_active")
    private boolean isActive;
    @Column(name = "created_date")
    private Timestamp createdDate;
    @Column(name = "updated_time")
    private Timestamp updatedTime;

    @OneToMany(fetch =  FetchType.LAZY, mappedBy = "resumeId")
    private List<Education> educations;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "resumeId")
    private List<Experience> experiences;
}
