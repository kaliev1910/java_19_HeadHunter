package com.example.java_19_headhunter.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class BasicDao {
    protected JdbcTemplate jdbcTemplate;
}
