package com.example.java_19_headhunter.service.impl;

import com.example.java_19_headhunter.dao.interfaces.ResumeDao;
import com.example.java_19_headhunter.dto.basicDtos.ResumeDto;
import com.example.java_19_headhunter.dto.createDto.ResumeCreateDto;
import com.example.java_19_headhunter.models.Resume;
import com.example.java_19_headhunter.service.ResumeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    @NotNull
    private final ResumeDao resumeDao;

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
    public List<ResumeDto> getResumesWithPaging(Integer page, Integer pageSize) {
        int count = resumeDao.getCount();
        int totalPages = count / pageSize;

        if (totalPages <= page) {
            page = totalPages;
        } else if (page < 0) {
            page = 0;
        }

        int offset = page * pageSize;

        List<ResumeDto> resumeDtos = new ArrayList<>();
        List<Resume> list = resumeDao.getAll(pageSize, offset);

        list.forEach(e -> resumeDtos.add(toDto(e)));
//        }
        return resumeDtos;
    }

    @Override
    public List<ResumeDto> getAll() {
        try {
            return resumeDao.getAll().stream().map(this::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error finding all Resumes ", e);
            throw e;
        }
    }

    @Override
    public List<ResumeDto> findByUserEmail(String userEmail) {
        try {
            return resumeDao.findByUserEmail(userEmail).stream().map(this::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error finding Resumes by userEmail: {}", userEmail, e);
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
    public int create(ResumeCreateDto resumeDto, Authentication authentication) {
        int resumeId;
        try {
            User user = (User) authentication.getPrincipal();
            Resume resume = fromDto(mapToResumeDto(resumeDto));
            resume.setCreatedDate(LocalDate.now());
            resume.setUpdatedTime(LocalDate.now());
            resume.setApplicantEmail(user.getUsername());
            resumeId = resumeDao.create(resume);
        } catch (Exception e) {
            log.error("Error inserting Resume: {}", resumeDto, e);
            throw e;
        }
        return resumeId;
    }

    @Override
    public void update(@Valid ResumeCreateDto resumeCreateDto, Authentication authentication) {
        try {

            Resume resume = fromCreateDto(resumeCreateDto, authentication);
            resume.setApplicantEmail(authentication.getName());
            resume.setUpdatedTime(LocalDate.now());
            resumeDao.update(resume);
        } catch (Exception e) {
            log.error("Error updating Resume:  name {} user {} ", resumeCreateDto.getName(), ((User) authentication.getPrincipal()).getUsername(), e);
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

    private Resume fromCreateDto(ResumeCreateDto resumeDto, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        return Resume.builder()
                .applicantEmail(user.getUsername())
                .categoryId(resumeDto.getCategoryId())
                .isActive(true)
                .name(resumeDto.getName())
                .expectedSalary(resumeDto.getExpectedSalary())
                .createdDate(LocalDate.now())
                .updatedTime(LocalDate.now())
                .build();
    }

    private Resume fromDto(ResumeDto resumeDto) {
        return Resume.builder()

                .applicantEmail(resumeDto.getApplicantEmail())
                .categoryId(resumeDto.getCategoryId())
                .isActive(resumeDto.isActive())
                .name(resumeDto.getName())
                .expectedSalary(resumeDto.getExpectedSalary())
                .createdDate(resumeDto.getCreatedTime())
                .updatedTime(LocalDate.now())
                .build();
    }

    private ResumeDto toDto(Resume resume) {
        return ResumeDto.builder()
                .id(resume.getId())
                .name(resume.getName())
                .applicantEmail(resume.getApplicantEmail())
                .categoryId(resume.getCategoryId())
                .isActive(resume.isActive())
                .expectedSalary(resume.getExpectedSalary())
                .createdTime(resume.getCreatedDate())
                .updatedTime(resume.getUpdatedTime())
                .build();
    }


    public static ResumeDto mapToResumeDto(ResumeCreateDto createDto) {
        ResumeDto resumeDto = new ResumeDto();
        resumeDto.setName(createDto.getName());
        resumeDto.setExpectedSalary(createDto.getExpectedSalary());
        resumeDto.setCategoryId(createDto.getCategoryId());
        resumeDto.setActive(true);
        resumeDto.setCreatedTime(LocalDate.now());
        resumeDto.setUpdatedTime(LocalDate.now());
        return resumeDto;
    }
}