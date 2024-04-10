package com.example.java_19_headhunter.dao.implementation;

import com.example.java_19_headhunter.dao.interfaces.CategoryDao;
import com.example.java_19_headhunter.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryDaoImpl extends BasicDaoImpl implements CategoryDao {

    public List<Category> getAllCategories() {
        String query = "SELECT * FROM categories";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Category.class));
    }
}
