package com.example.java_19_headhunter.dao.interfaces;

import com.example.java_19_headhunter.models.ContactInfo;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ContactInfoDao {
    void insert(ContactInfo contactInfo);

    void update(ContactInfo contactInfo);

    List<ContactInfo> findByResumeId(int resumeId);

    void deleteByResumeId(int resumeId);
}
