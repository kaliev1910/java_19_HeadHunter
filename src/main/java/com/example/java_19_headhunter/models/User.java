package com.example.java_19_headhunter.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class User {
    private int id;
    private String name;
    private String surname;
    private byte age;
    @Id
    private String email;
    private String password;
    private String avatar;
    @Column(name = "account_type")
    private String accountType;
    private boolean enabled;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "applicantEmail")
    private List<Resume> resumes;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "authorEmail")
    private List<Vacancy> vacancies;
}
