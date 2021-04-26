package debug.bean;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author longjie
 * 2021/4/26
 */
@Component
@Aspect
public class AspectBean {
	@Pointcut("execution (void debug.bean.*.call())")
	private void beforeMethod() {

	}

	@Before("beforeMethod()")
	public void before() {
		System.out.println(String.format("-----------开始时间戳  %s ------", System.currentTimeMillis()));
	}
}
