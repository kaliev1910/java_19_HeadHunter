package com.example.java_19_headhunter.repository;

import com.example.java_19_headhunter.models.Role;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO USER_ROLES(USER_EMAIL, ROLE_ID) VALUES (:email, :roleId)", nativeQuery = true)
    void addUserRole(@Param("roleId") int roleId, @Param("email") String email);
}
