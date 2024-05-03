package com.example.java_19_headhunter.repository;

import com.example.java_19_headhunter.models.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactInfoRepository extends JpaRepository<ContactInfo, Integer> {
//    @Query("select c from ContactInfo c where ")
//    ContactInfo findContactInfosByResumeId(int resumeId);

    @Query("select c from  ContactInfo c where c.resumeId = :resumeId ")
    List<ContactInfo> findCIListByResumeId(int resumeId);

    @Query("select c from  ContactInfo  c where  c.typeId = :typeId")
    ContactInfo findByTypeId(int typeId);

    void deleteByResumeId_Id(int resumeId);

}
