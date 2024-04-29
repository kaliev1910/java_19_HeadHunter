package com.example.java_19_headhunter.service.interfaces;

import com.example.java_19_headhunter.dto.basicDtos.ContactInfoDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactInfoService {

    void insert(ContactInfoDto contactInfoDto);

    void update(ContactInfoDto contactInfoDto);

    List<ContactInfoDto> findByResumeId(int resumeId);

    void deleteByResumeId(int resumeId);
}
