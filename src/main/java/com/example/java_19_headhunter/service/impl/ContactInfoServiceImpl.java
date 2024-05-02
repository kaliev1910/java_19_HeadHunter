package com.example.java_19_headhunter.service.impl;

import com.example.java_19_headhunter.dao.interfaces.ContactInfoDao;
import com.example.java_19_headhunter.dto.basicDtos.ContactInfoDto;
import com.example.java_19_headhunter.models.ContactInfo;
import com.example.java_19_headhunter.repository.ContactInfoRepository;
import com.example.java_19_headhunter.service.interfaces.ContactInfoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class ContactInfoServiceImpl implements ContactInfoService {
    private final ContactInfoDao contactInfoDao;
    private final ContactInfoRepository contactInfoRepository;



    @Override
    public List<ContactInfoDto> findByResumeId(int resumeId) {
        List<ContactInfo> contactInfos = contactInfoDao.findByResumeId(resumeId);
        if (contactInfos.isEmpty()) {
            return Collections.emptyList(); // Возвращаем пустой список, если контакты не найдены
        }
        return contactInfos.stream()
                .map(this::mapToContactInfoDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteByResumeId(int resumeId) {
        List<ContactInfo> contactInfos = contactInfoDao.findByResumeId(resumeId);
        if (!contactInfos.isEmpty()) { // Проверяем, есть ли контакты для удаления
            contactInfoDao.deleteByResumeId(resumeId);
        }
        // Если контакты отсутствуют, ничего не делаем
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

    private ContactInfo mapToContactInfo(ContactInfoDto contactInfoDto) {
        return ContactInfo.builder()
                .id(contactInfoDto.getId())
                .resumeId(contactInfoRepository.findContactInfoByResumeId(contactInfoDto.getResumeId()).getResumeId())
                .typeId(contactInfoRepository.findByTypeId(contactInfoDto.getTypeId()).getTypeId())
                .contactValue(contactInfoDto.getContactValue())
                .build();
    }

    private ContactInfoDto mapToContactInfoDto(ContactInfo contactInfo) {
        return ContactInfoDto.builder()
                .id(contactInfo.getId())
                .resumeId(contactInfo.getResumeId().getId())
                .typeId(contactInfo.getTypeId().getId())
                .contactValue(contactInfo.getContactValue())
                .build();
    }
}
