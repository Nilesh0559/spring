package com.project.app1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppConfig {

    /**
     * @param http
     * @return
     * @throws Exception
     *                   Approach 1 - This approach is default configuration of
     *                   spring security
     */
    // @Bean
    // SecurityFilterChain defaultSecurity(HttpSecurity http) throws Exception {
    // return http.authorizeHttpRequests().anyRequest().authenticated()
    // .and().formLogin().and().build();
    // }

    /**
     * Approach 2 - This method is modified to allow URI /api2 & /api3 to be
     * authenticated and /api1 to be permitted without authentication
     */
    // @Bean
    // SecurityFilterChain defaultSecurity(HttpSecurity http) throws Exception {
    // return http.authorizeHttpRequests(request -> request.requestMatchers("/api2",
    // "/api3").authenticated()
    // .requestMatchers("/api1").permitAll())
    // .formLogin(form -> form.permitAll())
    // .build();
    // }

    /**
     * Approach 3 - Defining in memory user details service to store users in memory
     * with default password encoder and configuring security filter chain based on
     * user roles and APIs
     */
    // @Bean
    // InMemoryUserDetailsManager userDetailService() {
    // UserDetails admin = User.withDefaultPasswordEncoder()
    // .username("admin")
    // .password("admin")
    // .roles("ADMIN")
    // .build();
    // UserDetails user = User.withDefaultPasswordEncoder()
    // .username("foo")
    // .password("foo")
    // .roles("USER")
    // .build();
    // return new InMemoryUserDetailsManager(admin, user);
    // }

    // @Bean
    // SecurityFilterChain defaultSecurity(HttpSecurity http) throws Exception {
    // return http.authorizeHttpRequests(request -> request.requestMatchers("api2",
    // "/api3").authenticated()
    // .requestMatchers("/api1").permitAll())
    // .formLogin(form -> form.permitAll())
    // .build();
    // }

    /**
     * Approach 4 - Defining in memory user details service to store users in memory
     * with password encoder (NoOpPasswordEncoder) and configuring security filter
     * chain based on user roles and APIs
     */

    @Bean
    PasswordEncoder encodePassword() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    InMemoryUserDetailsManager userDetailService() {
        UserDetails admin = User.withUsername("admin")
                .password(encodePassword().encode("admin"))
                .roles("ADMIN")
                .build();
        UserDetails user = User.withUsername("foo")
                .password(encodePassword().encode("foo"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    SecurityFilterChain defaultSecurity(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(request -> request.requestMatchers("/api2", "/api3").authenticated()
                .requestMatchers("/api1").permitAll())
                .formLogin(Customizer.withDefaults())
                .build();
    }
}
