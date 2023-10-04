package com.shop.common.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.common.exception.ApiErrorInfo;

public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public void commence(HttpServletRequest req, HttpServletResponse res,
			AuthenticationException authException) throws IOException, ServletException {

		res.setContentType("application/json;charset=UTF-8");
		
		ApiErrorInfo apiErrorInfo = new ApiErrorInfo();
		if (InsufficientAuthenticationException.class == authException.getClass()) {
			apiErrorInfo.setMessage("Not Logined!!!");
			res.setStatus(HttpStatus.UNAUTHORIZED.value());
		} else {
			apiErrorInfo.setMessage("Bas Request!!!");
			res.setStatus(HttpStatus.BAD_REQUEST.value());
		}
		
		String jsonString = objectMapper.writeValueAsString(apiErrorInfo);
		res.getWriter().write(jsonString);

	}

}
