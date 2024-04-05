package com.example.java_19_headhunter.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final PasswordEncoder encoder;
    private final DataSource dataSource;

    private static final String USER_QUERY = "select email,password,enabled from users where email=?";
    private static final String ROLES_QUERY = """
            select u.email, r.role 
            from user_roles ur inner join users u on u.email = ur.user_email
            inner join roles r on r.id = ur.role_id where u.email=?
            """;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(USER_QUERY)
                .authoritiesByUsernameQuery(ROLES_QUERY)
                .passwordEncoder(new BCryptPasswordEncoder());

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .authorizeRequests(authz -> authz
//                        .requestMatchers(new AntPathRequestMatcher("/user/register", HttpMethod.POST.name())).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/user/vacancies", HttpMethod.GET.name())).permitAll()
                        .anyRequest().permitAll());
        return http.build();
    }
}
