package com.helpsync.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desabilita CSRF
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**").permitAll() // Permite tudo em /api/**
                .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/api-docs/**").permitAll() // Libera o Swagger
                .anyRequest().authenticated() // Exige autenticação para o resto
            );
        
        return http.build();
    }
}
