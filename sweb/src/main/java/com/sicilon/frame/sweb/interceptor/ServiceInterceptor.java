package com.sicilon.frame.sweb.interceptor;

import java.io.IOException;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.sicilon.frame.sweb.config.WebConfig;

@Aspect
@Component
public class ServiceInterceptor {
	@Pointcut("execution(public * com.sicilon.project.service.*.*.*(..))")
	public void interceptor() {
	}

	@Before("interceptor()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		if(WebConfig.SERVICE_INTERCEP){
		}
	}

	@AfterReturning(pointcut = "interceptor()", returning = "returnObj")
	public void doAfter(JoinPoint joinPoint, Object returnObj) {
	}

	@AfterThrowing(pointcut = "interceptor()", throwing = "ex")
	public void afterThrow(JoinPoint joinPoint, Exception ex) throws IOException {
	}
	
	
}
