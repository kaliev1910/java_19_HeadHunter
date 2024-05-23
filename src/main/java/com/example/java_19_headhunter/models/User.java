package com.example.java_19_headhunter.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

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
    @Column(name = "reset_password_token")
    private String resetPasswordToken;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "applicantEmail")
    private List<Resume> resumes;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "authorEmail")
    private List<Vacancy> vacancies;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "USER_EMAIL"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles;

}
