package com.example.java_19_headhunter.service.impl;

import com.example.java_19_headhunter.dao.interfaces.ContactInfoDao;
import com.example.java_19_headhunter.dto.ContactInfoDto;
import com.example.java_19_headhunter.models.ContactInfo;
import com.example.java_19_headhunter.service.ContactInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactInfoServiceImpl implements ContactInfoService {
    private final ContactInfoDao contactInfoDao;

    @Autowired
    public ContactInfoServiceImpl(ContactInfoDao contactInfoDao) {
        this.contactInfoDao = contactInfoDao;
    }

    @Override
    public void insert(ContactInfoDto contactInfoDto) {
        ContactInfo contactInfo = mapToContactInfo(contactInfoDto);
        contactInfoDao.insert(contactInfo);
    }

    @Override
    public void update(ContactInfoDto contactInfoDto) {
        ContactInfo contactInfo = mapToContactInfo(contactInfoDto);
        contactInfoDao.update(contactInfo);
    }

    @Override
    public List<ContactInfoDto> findByResumeId(int resumeId) {
        List<ContactInfo> contactInfos = contactInfoDao.findByResumeId(resumeId);
        return contactInfos.stream()
                .map(this::mapToContactInfoDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteByResumeId(int resumeId) {
        contactInfoDao.deleteByResumeId(resumeId);
    }

    private ContactInfo mapToContactInfo(ContactInfoDto contactInfoDto) {
        return ContactInfo.builder()
                .id(contactInfoDto.getId())
                .resumeId(contactInfoDto.getResumeId())
                .typeId(contactInfoDto.getTypeId())
                .contactValue(contactInfoDto.getContactValue())
                .build();
    }

    private ContactInfoDto mapToContactInfoDto(ContactInfo contactInfo) {
        return ContactInfoDto.builder()
                .id(contactInfo.getId())
                .resumeId(contactInfo.getResumeId())
                .typeId(contactInfo.getTypeId())
                .contactValue(contactInfo.getContactValue())
                .build();
    }
}
