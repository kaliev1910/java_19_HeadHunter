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
    //TODO добавить методы для пагинации
    //TODO переделать методы чтобы возвращал по ордеру

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE users SET name = ?, surname = ?, age = ?,  password = ?, avatar = ?, account_type = ? WHERE EMAIL = ?";
        jdbcTemplate.update(sql, user.getName(), user.getSurname(), user.getAge(),
                user.getPassword(), user.getAvatar(), user.getAccountType(), user.getEmail());
    }

    @Override
    public void createUser(User user) {
        String sql = "INSERT INTO users (name, surname, age, email, password, avatar, account_type,ENABLED) VALUES (?, ?, ?, ?, ?,?, ?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getSurname(), user.getAge(),
                user.getEmail(), user.getPassword(), user.getAvatar(), user.getAccountType(), user.isEnabled());

        String sqlUserRole = "INSERT INTO user_roles (user_email, role_id) VALUES (?, ?)";
        int roleId = 0;
        if (user.getAccountType().equalsIgnoreCase( "APPLICANT")) {
            roleId=1;
        } else if (user.getAccountType().equalsIgnoreCase("EMPLOYER")) {
            roleId=2;
        }

        jdbcTemplate.update(sqlUserRole, user.getEmail(),roleId);

    }


    @Override
    public List<User> getUsers() {
        String sql = """
                select * from users order by NAME desc
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
                SELECT * FROM users WHERE name = ?;
                    """;
        return Optional.ofNullable(DataAccessUtils.singleResult(jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), name)));
    }

    @Override
    public boolean userExists(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }

    @Override
    public boolean getUserType(User user) {
        return false;
    }


}
