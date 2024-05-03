package com.example.java_19_headhunter.repository;

import com.example.java_19_headhunter.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
