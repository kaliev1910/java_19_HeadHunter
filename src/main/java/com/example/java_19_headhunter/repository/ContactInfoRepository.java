package com.example.java_19_headhunter.repository;

import com.example.java_19_headhunter.models.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactInfoRepository extends JpaRepository<ContactInfo, Integer> {
//    @Query("select c from ContactInfo c where ")
//    ContactInfo findContactInfosByResumeId(int resumeId);


    List<ContactInfo> findContactInfosByResumeId_Id(int resumeId);

    

    void deleteByResumeId_Id(int resumeId);

}
