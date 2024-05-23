package com.godstimeProjects.librarySystem.secureCH.config;

import com.godstimeProjects.librarySystem.secureCH.jwt.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.godstimeProjects.librarySystem.secureCH.entity.Permission.*;
import static com.godstimeProjects.librarySystem.secureCH.entity.Role.ADMIN;
import static com.godstimeProjects.librarySystem.secureCH.entity.Role.MEMBER;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationProvider authProvider;
    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                request.requestMatchers("/api/v1/auth/**")
                        .permitAll()
                        .requestMatchers("api/v1/users/**").hasAnyRole(ADMIN.name(), MEMBER.name())
                        .requestMatchers(GET, "/api/v1/users/**").hasAnyAuthority(ADMIN_READ.name(), MEMBER_READ.name())
                        .requestMatchers(POST, "/api/v1/users/**").hasAnyAuthority(ADMIN_CREATE.name(), MEMBER_CREATE.name())
                        .anyRequest()
                        .authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }
}
