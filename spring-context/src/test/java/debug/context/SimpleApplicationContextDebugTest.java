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

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author carllongj
 * 2021/5/4 14:05
 */
public class SimpleApplicationContextDebugTest {
	@Test
	public void testSimpleApplicationContext() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/debug/applicationContext-simple.xml");
		context.getBean("a");
	}
}
