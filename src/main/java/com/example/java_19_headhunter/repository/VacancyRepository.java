package com.example.java_19_headhunter.repository;

import com.example.java_19_headhunter.models.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VacancyRepository extends JpaRepository<Vacancy, Integer> {
    List<Vacancy> findVacanciesByCategoryId_Id(int categoryId);

    List<Vacancy> findVacanciesByAuthorEmail_Email(String authorEmail);

    @Query("select v from Vacancy v where v.isActive = true")
    List<Vacancy> findActiveVacancies();

    Page<Vacancy> findAllByOrderByUpdatedTimeDesc(Pageable pageable);


    List<Vacancy> findActiveVacanciesByAuthorEmail_id(int userId);

    Optional<Vacancy> findVacancyByAuthorEmail_Email(String name);

    List<Vacancy> findVacanciesBySalaryBetween(int salaryFrom, int salaryTo);
}
