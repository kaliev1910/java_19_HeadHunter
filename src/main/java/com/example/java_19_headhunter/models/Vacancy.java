package com.example.java_19_headhunter.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vacancies")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JoinColumn(name = "author_email")
    @ManyToOne
    public User authorEmail;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "category_id")
    public Category categoryId;
    private int salary;

    private int expFrom;
    private int expTo;
    private boolean isActive;
    private Timestamp createdDate;
    private Timestamp updateTime;

}
