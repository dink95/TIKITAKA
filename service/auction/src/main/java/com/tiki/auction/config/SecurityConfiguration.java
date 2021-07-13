package com.tiki.auction.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsUtils;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  //csrf
                .cors().disable() //cors
                .headers().frameOptions().disable();
        http
                .authorizeRequests()
                .anyRequest().permitAll(); //ID, PWD를 사용하지 않기 위해서
    }




}
