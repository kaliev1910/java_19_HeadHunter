package com.example.java_19_headhunter.service.interfaces;

import com.example.java_19_headhunter.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    public List<Category> getAllCategories();
}
