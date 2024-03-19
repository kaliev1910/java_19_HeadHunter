package com.example.java_19_headhunter.service.impl;

import com.example.java_19_headhunter.dao.interfaces.ResumeDao;
import com.example.java_19_headhunter.dto.ResumeDto;
import com.example.java_19_headhunter.models.Resume;
import com.example.java_19_headhunter.service.ResumeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeDao resumeDao;

    private ResumeDto toDto(Resume resume) {
        return ResumeDto.builder()
                .id(resume.getId())
                .applicantId(resume.getApplicantId())
                .name(resume.getName())
                .expectedSalary(resume.getExpectedSalary())
                .createdTime(resume.getCreatedTime())
                .updatedTime(resume.getUpdatedTime())
                .build();
    }

    private Resume fromDto(ResumeDto resumeDto) {
        return Resume.builder()
                .id(resumeDto.getId())
                .applicantId(resumeDto.getApplicantId())
                .name(resumeDto.getName())
                .expectedSalary(resumeDto.getExpectedSalary())
                .createdTime(resumeDto.getCreatedTime())
                .updatedTime(resumeDto.getUpdatedTime())
                .build();
    }

    @Override
    public List<ResumeDto> findByCategory(int category) {
        try {
            return resumeDao.findByCategory(category).stream().map(this::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error finding Resumes by category: {}", category, e);
            throw e;
        }
    }

    @Override
    public List<ResumeDto> findByUserId(int userId) {
        try {
            return resumeDao.findByUserId(userId).stream().map(this::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error finding Resumes by userId: {}", userId, e);
            throw e;
        }
    }

    @Override
    public ResumeDto findById(int id) {
        try {
            return toDto(resumeDao.findById(id));
        } catch (Exception e) {
            log.error("Error finding Resume by id: {}", id, e);
            throw e;
        }
    }

    @Override
    public void create(ResumeDto resumeDto) {
        try {
            resumeDao.create(fromDto(resumeDto));
        } catch (Exception e) {
            log.error("Error inserting Resume: {}", resumeDto, e);
            throw e;
        }
    }

    @Override
    public void update(ResumeDto resumeDto) {
        try {
            resumeDao.update(fromDto(resumeDto));
        } catch (Exception e) {
            log.error("Error updating Resume: {}", resumeDto, e);
            throw e;
        }
    }

    @Override
    public void deleteById(int id) {
        try {
            resumeDao.deleteById(id);
        } catch (Exception e) {
            log.error("Error deleting Resume by id: {}", id, e);
            throw e;
        }
    }
}