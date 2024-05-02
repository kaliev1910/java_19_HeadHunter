package com.example.java_19_headhunter.repository;

import com.example.java_19_headhunter.models.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactInfoRepository extends JpaRepository<ContactInfo, Integer> {
}
