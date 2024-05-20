package com.example.java_19_headhunter.repository;

import com.example.java_19_headhunter.models.ContactInfo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface ContactInfoRepository extends JpaRepository<ContactInfo, Integer> {
//    @Query("select c from ContactInfo c where ")
//    ContactInfo findContactInfosByResumeId(int resumeId);


    List<ContactInfo> findContactInfosByResumeId_Id(int resumeId);


    @Modifying
    @Transactional
    void deleteByResumeId_Id(int resumeId);

}
