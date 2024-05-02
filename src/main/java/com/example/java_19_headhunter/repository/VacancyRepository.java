package com.example.java_19_headhunter.repository;

import com.example.java_19_headhunter.models.Category;
import com.example.java_19_headhunter.models.User;
import com.example.java_19_headhunter.models.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Integer> {
    @Query("select v from Vacancy v where  Category.id = :categoryId")
    List<Vacancy> getListByCategoryId(int categoryId);

    @Query("select v from Vacancy v where User.email = :authorEmail")
    List<Vacancy> getListByAuthorEmail(String authorEmail);

    @Query("select v from Vacancy v where Vacancy.isActive = true")
    List<Vacancy> findActiveVacancies();

    @Query("select v from Vacancy v where Vacancy.authorEmail = :authorEmail")
    User findByAuthorEmail(String authorEmail);

    @Query("select v from Vacancy v where v.categoryId.id = :categoryId")
    Category findByCategoryId(int categoryId);
}
