package com.example.java_19_headhunter.repository;

import com.example.java_19_headhunter.models.ContactInfo;
import com.example.java_19_headhunter.models.ContactType;
import com.example.java_19_headhunter.models.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContactInfoRepository extends JpaRepository<ContactInfo, Integer> {

    ContactInfo findContactInfoByResumeId(int resumeId);

    ContactInfo findByTypeId(int typeId);
}
