package com.example.java_19_headhunter.repository;

import com.example.java_19_headhunter.models.Category;
import com.example.java_19_headhunter.models.Resume;
import com.example.java_19_headhunter.models.User;
import com.example.java_19_headhunter.models.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {
    List<Resume> findResumesByApplicantEmail_Email(String userEmail);

    Resume findResumeById(int id);

    List<Resume> findResumesByCategoryId_Id(int category);
    Page<Resume> findAllByOrderByUpdatedTimeDesc(Pageable pageable);
//    User findUserByApplicantEmail(String applicantEmail);
}
