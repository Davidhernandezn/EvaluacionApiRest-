package com.mifel.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Profile("dev")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Configuración para la consola de H2
        http.authorizeRequests()
        .antMatchers("/h2-ui/**").permitAll()
        .antMatchers("/api/users/**").permitAll() // Permitir el acceso a /api/users sin autenticación
        .anyRequest().authenticated()
        .and().csrf().ignoringAntMatchers("/h2-ui/**") // Ignorar CSRF para la consola de H2
        .and().headers().frameOptions().sameOrigin()  // Permitir la carga del frame de H2
        .and().csrf().disable();
}}
