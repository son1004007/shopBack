package com.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.shop.common.interceptor.AccessLoggingInterceptor;

@Configuration
public class IntercepterConfig implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(
				accessLonggingInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/resources/**")
				.excludePathPatterns("/users/**");
		
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
	@Bean
	public HandlerInterceptor accessLonggingInterceptor() {
		return new AccessLoggingInterceptor();
	}

}
