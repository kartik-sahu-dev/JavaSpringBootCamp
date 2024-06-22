package com.example.learnspringaop.aopexample.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class PerformanceTrackingAspects {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
//	@Around("execution(* com.example.learnspringaop.aopexample.*.*.*(..))")
	@Around("com.example.learnspringaop.aopexample.aspects.CommonPointCutConfig.trackTimeAnnotation()")
	public Object findExecutionTime(ProceedingJoinPoint proceedingJointPoint) throws Throwable {
		
		//start a timer
		long startTimerMillis = System.currentTimeMillis();
		
		//execute method
		Object obj = proceedingJointPoint.proceed();
		
		//end timer  
		long stopTimerMillis = System.currentTimeMillis();
	
		//calculate time
		long executionTime = stopTimerMillis - startTimerMillis;
		
		logger.info("Around Aspect - {} method executed in {} ms",proceedingJointPoint,executionTime);
		
		return obj;
	}
}
