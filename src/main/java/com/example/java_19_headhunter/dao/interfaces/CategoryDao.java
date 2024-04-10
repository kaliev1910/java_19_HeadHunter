package com.example.java_19_headhunter.dao.interfaces;

import com.example.java_19_headhunter.models.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryDao {
    public List<Category> getAllCategories();
}
