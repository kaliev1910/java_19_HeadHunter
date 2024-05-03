package com.example.java_19_headhunter.models;

import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "exp_from")
    private int expFrom;
    @Column(name = "exp_to")
    private int expTo;
    @Column(name = "is_active")
    private boolean isActive;
    @Column(name = "created_date")
    private Timestamp createdDate;
    @Column(name = "updated_time")
    private Timestamp updatedTime;


}
