package com.core.common.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName: LogAspect
 * @Description: 日志类，写入登陆信息到数据库，控制台打出信息
 * @author 孙文祥
 * @date 2017年8月13日 下午4:28:23
 *
 */
@Aspect
@Component
public class LogAspect {


	public LogAspect() {
	}

	@Pointcut("execution(* com.sys.controller..*.*(..))")
	public void controllerAspect() {

	}

	@Before("controllerAspect()")
	public void controllerBeforeShow(JoinPoint jp) {
		// System.out.println("--------------------------------------------"+jp.getSignature().getName());
		System.out.println("-----------------------------------------------------------");
	}
}
