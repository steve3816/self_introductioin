package com.example.selfintro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests((requests) -> requests
                                                .requestMatchers("/", "/login/code", "/register", "/css/**",
                                                                "/verification-code", "/h2-console/**")
                                                .permitAll()
                                                .requestMatchers("/public/**").permitAll()
                                                .anyRequest().authenticated())
                                .csrf((csrf) -> csrf
                                                .ignoringRequestMatchers("/h2-console/**"))
                                .headers((headers) -> headers
                                                .frameOptions((frame) -> frame.sameOrigin()))
                                .formLogin((form) -> form
                                                .loginPage("/login")
                                                .defaultSuccessUrl("/profile", true)
                                                .permitAll())
                                .logout((logout) -> logout.permitAll());

                return http.build();
        }
}
