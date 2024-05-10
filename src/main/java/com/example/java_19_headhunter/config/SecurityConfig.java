package com.example.java_19_headhunter.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
//                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
//                .formLogin(AbstractHttpConfigurer::disable)
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler((request, response, authentication) -> {
                            for (GrantedAuthority authority : authentication.getAuthorities()) {
                                if (authority.getAuthority().equals("APPLICANT")) {
                                    response.sendRedirect("/resumes");
                                    return;
                                } else if (authority.getAuthority().equals("EMPLOYER")) {
                                    response.sendRedirect("/vacancies");
                                    return;
                                }
                            }
                            ;
                        })
                        .permitAll())
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll())
                .authorizeHttpRequests(authz -> authz
                                .requestMatchers("/profile").authenticated()
                                .requestMatchers("/resumes").authenticated()
                                .requestMatchers("/resumes").hasAnyAuthority("EMPLOYER")
                                .requestMatchers("/chat").authenticated()

//                        .requestMatchers(HttpMethod.GET, "/resume/**").authenticated()
                                .requestMatchers("/resume/*/edit").hasAnyAuthority("APPLICANT")
                                .requestMatchers("/vacancy/*/edit").hasAnyAuthority("EMPLOYER")
//                        .requestMatchers(HttpMethod.GET, "/").permitAll()
                                .anyRequest().permitAll()
                )
                .exceptionHandling(handle -> handle
                        .accessDeniedPage("/error")

                )
        ;

        return http.build();
    }

}
