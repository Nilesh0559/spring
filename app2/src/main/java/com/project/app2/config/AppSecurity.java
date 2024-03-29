package com.project.app2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppSecurity {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(request -> request.requestMatchers("/hello")
                        .authenticated()
                        .anyRequest().permitAll())
                .build();
    }
}
