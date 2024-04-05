package com.example.java_19_headhunter.dao.implementation;

import com.example.java_19_headhunter.dao.interfaces.ContactInfoDao;
import com.example.java_19_headhunter.models.ContactInfo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ContactInfoDaoImpl extends BasicDaoImpl implements ContactInfoDao {

    @Override
    public void insert(ContactInfo contactInfo) {
        String sql = "INSERT INTO contacts_info (resume_id, type_id, contact_value) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, contactInfo.getResumeId(), contactInfo.getTypeId(), contactInfo.getContactValue());
    }

    @Override
    public void update(ContactInfo contactInfo) {
        String sql = "UPDATE contacts_info SET type_id = ?, contact_value = ? WHERE id = ?";
        jdbcTemplate.update(sql, contactInfo.getTypeId(), contactInfo.getContactValue(), contactInfo.getId());
    }

    @Override
    public List<ContactInfo> findByResumeId(int resumeId) {
        String sql = "SELECT * FROM contacts_info WHERE resume_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ContactInfo.class), resumeId);
    }

    @Override
    public void deleteByResumeId(int resumeId) {
        String sql = "DELETE FROM contacts_info WHERE resume_id = ?";
        jdbcTemplate.update(sql, resumeId);
    }

}