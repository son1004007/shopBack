package com.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableWebSecurity
public class SecurityConfig {
	
	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		log.info("security config ...");
		
		// 폼 로그인 기능과 베이직 인증 비활성화
		http.formLogin().disable()
		.httpBasic().disable();
		
		// CSRF 방지 기능 기능 비활성화
		http.csrf().disable();
		
		return http.build();
	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		
//	}
//	
	@Bean
	public SecurityFilterChain filterChain(AuthenticationManagerBuilder auth) throws Exception {
		return (SecurityFilterChain) auth.build(); 
		
	}
	
	// 비밀번호 암호처리기 생성
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
}
