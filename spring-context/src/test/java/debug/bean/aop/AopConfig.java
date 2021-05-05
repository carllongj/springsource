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

package debug.bean.aop;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author carllongj
 * 2021/5/4 19:46
 */
@Component
@Aspect
public class AopConfig {

	@Pointcut("execution( * debug.bean.aop.advice.Business.doPrint(..))")
	public void run() {
	}

	@Before("run()")
	public void before() {
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
}
