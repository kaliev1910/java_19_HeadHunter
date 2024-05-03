package com.example.java_19_headhunter.service.impl;

import com.example.java_19_headhunter.models.Category;
import com.example.java_19_headhunter.repository.CategoryRepository;
import com.example.java_19_headhunter.service.interfaces.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }


}
