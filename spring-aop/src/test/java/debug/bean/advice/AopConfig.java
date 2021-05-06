/*
 * Copyright 2021 carllongj
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package debug.bean.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * @author carllongj
 * 2021/5/4 19:46
 */
@Aspect
public class AopConfig {

	@Pointcut("execution(* debug.bean.business.*.*(..))")
	public void run() {
	}


	@Before("run()")
	public void before(JoinPoint joinPoint) {
		System.out.println(joinPoint.getSignature().toLongString());
		System.out.println("method invoke before");
	}

	@After("run()")
	public void after() {
		System.out.println("method invoke after");
	}

	@AfterReturning("run()")
	public void afterReturning() {
		System.out.println("method invoke after returning");
	}

	@AfterThrowing("run()")
	public void afterThrowing() {
		System.out.println("method invoke after throwing");
	}


	@Around("run()")
	public void around(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("invoke Before");
		Object[] objects = joinPoint.getArgs();
		System.out.println(Arrays.toString(objects));
		// 若调用带参数的方法,则表示将这些参数传递给下层方法
		joinPoint.proceed(new Object[]{"test"});
		System.out.println("invoke after");
	}
}
