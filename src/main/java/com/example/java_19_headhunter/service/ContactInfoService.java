package com.example.java_19_headhunter.service;

import com.example.java_19_headhunter.dto.ContactInfoDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactInfoService {

    void insert(ContactInfoDto contactInfoDto);

    void update(ContactInfoDto contactInfoDto);

    List<ContactInfoDto> findByResumeId(int resumeId);

    void deleteByResumeId(int resumeId);
}
