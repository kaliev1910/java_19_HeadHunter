package com.example.java_19_headhunter.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final PasswordEncoder encoder;
    private final DataSource dataSource;

    private static final String USER_QUERY = "select email,password,enabled from users where email=?";
    private static final String ROLES_QUERY = "select u.email, r.name from user_roles ur inner join users u on u.email = ur.user_email inner join roles r on r.id = ur.role_id where u.email=?";

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(USER_QUERY)
                .authoritiesByUsernameQuery(ROLES_QUERY)
                .passwordEncoder(new BCryptPasswordEncoder());

    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .antMatchers("/user/register",
//                                "/user/users",
//                                "/user/exists/{email}",
//                                "/user/users/name/{name}",
//                                "/user/users/phoneNumber/{phoneNumber}",
//                                "/user/users/type",
//                                "/resume/resumes",
//                                "/resume/resumes/category/{id}",
//                                "/user/vacancies",
//                                "/user/vacancies/active",
//                                "/user/vacancies/category/{id}",
//                                "/user/vacancies/salary").permitAll()
//
//                        // These URL patterns require authentication
//                        .antMatchers("/resume/resume",
//                                "/resume/resume",
//                                "/resume/resume/{id}",
//                                "/resume/resumes/email/{email}",
//                                "/resume/resumes/{id}",
//                                "/user/apply/{vacancyId}",
//                                "/user/vacancies/applicant/email/{email}",
//                                "/user/vacancies/applicant/{id}").authenticated()
//
//
//                        // These URL patterns require "FULL", "WRITE_POSTS" authority
//                        .requestMatchers(AntPathRequestMatcher.antMatcher("/hh/redact/**"),
//                                AntPathRequestMatcher.antMatcher("/hh/guest/**"),
//                                AntPathRequestMatcher.antMatcher("/hh/profile/**")).hasAnyAuthority("FULL", "WRITE_POSTS")
//
//                        // These URL patterns require "FULL" authority
//                        .requestMatchers(AntPathRequestMatcher.antMatcher("/employer/**")).hasAuthority("FULL")
//
//                        // These URL patterns require "WRITE_POSTS" authority
//                        .requestMatchers(AntPathRequestMatcher.antMatcher("/applicant/**")).hasAuthority("WRITE_POSTS")
//                )
//                .formLogin(Customizer.withDefaults())
//                .logout(Customizer.withDefaults());
//
//        return http.build();
//    }
}
