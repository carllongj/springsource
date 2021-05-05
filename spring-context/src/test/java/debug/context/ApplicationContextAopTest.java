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

package debug.context;

import debug.bean.aop.advice.Business;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author carllongj
 * 2021/5/4 20:07
 */
public class ApplicationContextAopTest {

	ApplicationContext context;

	@BeforeEach
	public void init() {
		context = new ClassPathXmlApplicationContext("classpath:/debug/applicationContext-aop.xml");
	}

	@Test
	public void aopSimpleTest() {
		Business business = context.getBean(Business.class);
		business.doPrint("hello");
	}
}
