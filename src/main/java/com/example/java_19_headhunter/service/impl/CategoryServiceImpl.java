package com.example.java_19_headhunter.service.impl;

import com.example.java_19_headhunter.dao.interfaces.CategoryDao;
import com.example.java_19_headhunter.models.Category;
import com.example.java_19_headhunter.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }


}
