package com.godstimeProjects2023.librarySystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//@RequiredArgsConstructor
public class WebbSecurityConfig {

    private final UserDetailsService userDetailsService;

    public WebbSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .authorizeHttpRequests(config -> config
                        .requestMatchers("/", "/book","/search","/contact")
                        .permitAll()
                        .requestMatchers("/css/**","/image/**","/js/**").permitAll()
                        .requestMatchers("/account/**").hasRole("USER")
                        .requestMatchers("/addBookToLIst","/account/books/**").hasRole("USER")
                        .requestMatchers("/test", "/admin/**").hasRole("/ADMIN")
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form.loginPage("/login")
                        .loginProcessingUrl("/authUser")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .logout(logout -> logout.logoutSuccessUrl("/")
                        .permitAll());
        return httpSecurity.build();
    }
}
