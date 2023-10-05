package com.shop.common.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.shop.common.service.AccessLogService;
import com.shop.domain.AccessLog;
import com.shop.common.util.NetUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccessLoggingInterceptor implements HandlerInterceptor {

	@Autowired
	private AccessLogService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		String requestUri = request.getRequestURI();
		
		String remoteAddr = NetUtils.getIp(request);
		
		log.info("requestURL : " + requestUri);
		log.info("remoteAddr : " + remoteAddr);
		
		if(handler instanceof HandlerMethod) {
			HandlerMethod  handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			
			Class<?> clazz = method.getDeclaringClass();
			
			String className = clazz.getName();
			String classSimpleName = clazz.getSimpleName();
			String methodName = method.getName();
			
			log.info("[ACCESS CONTROLLER]" + className + "." + methodName);
			
			AccessLog accessLog = new AccessLog();
			
			accessLog.setRequestUri(requestUri);
			accessLog.setRemoteAddr(remoteAddr);
			accessLog.setClassName(className);
			accessLog.setClassSimpleName(classSimpleName);
			accessLog.setMethodName(methodName);
			
			service.register(accessLog);
			
		} else {
			log.info("handler : " + handler);
		}
	}
	
}
