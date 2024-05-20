package com.example.java_19_headhunter.service.impl;

import com.example.java_19_headhunter.dto.updateDto.ResumeUpdateDto;
import com.example.java_19_headhunter.dto.basicDtos.ResumeDto;
import com.example.java_19_headhunter.dto.createDto.ResumeCreateDto;
import com.example.java_19_headhunter.models.Resume;
import com.example.java_19_headhunter.models.User;
import com.example.java_19_headhunter.repository.ResumeRepository;
import com.example.java_19_headhunter.repository.UserRepository;
import com.example.java_19_headhunter.service.interfaces.ResumeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    @NotNull
    private final UserRepository userRepository;
    private final ResumeRepository resumeRepository;

    @Override
    public List<ResumeDto> findByCategory(int category) {
        try {
            return resumeRepository.findResumesByCategoryId_Id(category).stream().map(this::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error finding Resumes by category: {}", category, e);
            throw e;
        }
    }

    @Override
    public Page<ResumeDto> getResumesWithPaging(Pageable pageable) {
        return resumeRepository.findAll(pageable)
                .map(this::toDto);
    }

    @Override
    public Page<ResumeDto> getResumesWithPagingByCategories(Pageable pageable, Integer categoryId) {
        return resumeRepository.findByCategoryId_Id(pageable, categoryId).map(this::toDto);
    }

    @Override
    public List<ResumeDto> getAll() {
        try {
            return resumeRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error finding all Resumes ", e);
            throw e;
        }
    }

    @Override
    public List<ResumeDto> findByUserEmail(String userEmail) {
        try {
            return resumeRepository.findResumesByApplicantEmail_Email(userEmail).stream().map(this::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error finding Resumes by userEmail: {}", userEmail, e);
            throw e;
        }
    }

    @Override
    public ResumeDto findById(int id) {
        try {
            return toDto(resumeRepository.findResumeById(id).get());
        } catch (Exception e) {
            log.error("Error finding Resume by id: {}", id, e);
            throw e;
        }
    }



    @Override
    public int create(ResumeCreateDto resumeDto, Authentication authentication) {
        Resume resumeId;
        try {
            User user = (User) authentication.getPrincipal();
            Resume resume = fromDto(mapToResumeDto(resumeDto));
            resume.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
            resume.setUpdatedTime(Timestamp.valueOf(LocalDateTime.now()));
            resume.setApplicantEmail(userRepository.findUserByEmail(user.getEmail()).orElseThrow());
            resumeId = resumeRepository.save(resume);
        } catch (Exception e) {
            log.error("Error inserting Resume: {}", resumeDto, e);
            throw e;
        }
        return resumeId.getId();
    }

    @Override
    public void update(@Valid ResumeUpdateDto resumeUpdateDto, Authentication authentication) {
        try {
            Optional<User> userOptional = userRepository.findUserByEmail(authentication.getName());
            if (!userOptional.isPresent()) {
                throw new RuntimeException("User not found: " + authentication.getName());
            }
            User user = userOptional.get();

            Optional<Resume> resumeOptional = resumeRepository.findResumeById(resumeUpdateDto.getId());
            if (resumeOptional.isEmpty()) {
                throw new RuntimeException("Resume not found for user: " + user.getEmail());
            }
            Resume resume = resumeOptional.get();

            // Update resume fields from DTO
            resume.setName(resumeUpdateDto.getName());
            // Update other fields similarly

            resume.setUpdatedTime(Timestamp.valueOf(LocalDateTime.now()));

            resumeRepository.save(resume);
        } catch (Exception e) {
            log.error("Error updating Resume: name {} user {} ", resumeUpdateDto.getName(), ((User) authentication.getPrincipal()).getEmail(), e);
            throw e;
        }
    }


    @Override
    public void deleteById(int id) {
        try {
            resumeRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Error deleting Resume by id: {}", id, e);
            throw e;
        }
    }

    private Resume fromCreateDto(ResumeCreateDto resumeDto, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        return Resume.builder()
                .applicantEmail(userRepository.findUserByEmail(user.getEmail()).get())
                .categoryId(resumeRepository.findResumeById(resumeDto.getCategoryId()).get().getCategoryId())
                .isActive(true)
                .name(resumeDto.getName())
                .expectedSalary(resumeDto.getExpectedSalary())
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .updatedTime(Timestamp.valueOf(LocalDateTime.now()))
                .build();
    }

    private Resume fromUpdateDto(ResumeUpdateDto resumeDto, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        return Resume.builder()
                .applicantEmail(userRepository.findUserByEmail(user.getEmail()).get())
                .categoryId(resumeRepository.findResumeById(resumeDto.getId()).get().getCategoryId())
                .isActive(true)
                .name(resumeDto.getName())
                .expectedSalary(resumeDto.getExpectedSalary())
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .updatedTime(Timestamp.valueOf(LocalDateTime.now()))
                .build();
    }

    private Resume fromDto(ResumeDto resumeDto) {
        return Resume.builder()

                .applicantEmail(resumeRepository.findResumeById(resumeDto.getId()).get().getApplicantEmail())
                .categoryId(resumeRepository.findResumeById(resumeDto.getId()).get().getCategoryId())
                .isActive(resumeDto.isActive())
                .name(resumeDto.getName())
                .expectedSalary(resumeDto.getExpectedSalary())
                .createdDate(resumeDto.getCreatedTime())
                .updatedTime(Timestamp.valueOf(LocalDateTime.now()))
                .build();
    }

    private ResumeDto toDto(Resume resume) {
        return ResumeDto.builder()
                .id(resume.getId())
                .name(resume.getName())
                .applicantEmail(resume.getApplicantEmail().getEmail())
                .categoryId(resume.getCategoryId().getId())
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
        resumeDto.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));
        resumeDto.setUpdatedTime(Timestamp.valueOf(LocalDateTime.now()));
        return resumeDto;
    }
}