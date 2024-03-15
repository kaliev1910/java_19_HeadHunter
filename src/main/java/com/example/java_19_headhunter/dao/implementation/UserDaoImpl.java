package com.example.java_19_headhunter.dao.implementation;

import com.example.java_19_headhunter.dao.interfaces.UserDao;
import com.example.java_19_headhunter.models.User;
import lombok.AllArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void createUser(User user) {

    }

    @Override
    public List<User> getUsers() {
        String sql = """
                select * from users
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String sql = """
                select * from users where email = ?;
                """;
        return Optional.ofNullable(DataAccessUtils.singleResult(jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), email)));
    }

    @Override
    public Optional<User> findByPhoneNumber(String phoneNumber) {
        String sql = """

                SELECT u.* FROM users u JOIN contacts_info ci ON u.id = ci.resume_id 
                WHERE ci.contact_value = ? AND ci.type_id = (SELECT id FROM contact_types WHERE type = 'Phone');
                         """;
        return Optional.ofNullable(DataAccessUtils.singleResult(jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), phoneNumber)));
    }

    @Override
    public Optional<User> findByName(String name) {
        String sql = """
                SELECT * FROM users WHERE name LIKE ?;
                    """;
        return Optional.ofNullable(DataAccessUtils.singleResult(jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), name)));
    }

    @Override
    public boolean userExists(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }

}
