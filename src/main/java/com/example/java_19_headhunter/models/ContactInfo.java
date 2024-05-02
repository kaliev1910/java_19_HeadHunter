package com.example.java_19_headhunter.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "contacts_info")
public class ContactInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JoinColumn(name = "resume_id")
    @ManyToOne
    private Resume resumeId;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private ContactType typeId;
    private String contactValue;


}