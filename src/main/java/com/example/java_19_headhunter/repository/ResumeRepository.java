package com.example.java_19_headhunter.repository;

import com.example.java_19_headhunter.models.Resume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {
    List<Resume> findResumesByApplicantEmail_Email(String userEmail);

    Optional<Resume> findResumeById(int id);

    List<Resume> findResumesByCategoryId_Id(int category);

    Page<Resume> findAllByOrderByUpdatedTimeDesc(Pageable pageable);
//    User findUserByApplicantEmail(String applicantEmail);
}
