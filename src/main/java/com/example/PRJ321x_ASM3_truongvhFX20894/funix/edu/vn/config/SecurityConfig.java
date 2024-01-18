package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());

        httpSecurity.authorizeHttpRequests(configuer ->
                configuer.requestMatchers("/authenticate", "/register", "/forgotPassword", "/resetPassword").permitAll()
                        .requestMatchers("/api/cancelSchedule", "/api/doctor/schedule", "/api/patients",
                                "api/doctor/patient", "api/doctor/extrainfos", "/doctor/sendInfo").hasAuthority("Doctor")
                        .requestMatchers("/admin/unlockAcc", "/admin/lockAcc", "/registerDoc",
                                "/admin/schedule/patient/{patientId}", "/admin/schedule/doctor/{doctorId}").hasAuthority("Admin")
                        .anyRequest().authenticated());
        httpSecurity.exceptionHandling((exception) -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint));
        httpSecurity.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity.csrf(csrf -> csrf.disable());

        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/authenticate");
    }
}
