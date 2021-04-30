package debug.context;

import debug.bean.*;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author longjie
 * 2021/4/20
 */
public class ApplicationContextBeanDebugTest {

	@Test
	public void testLoadClasspathXmlApplicationContext() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/debug/applicationContext.xml");
		Person person = context.getBean(Person.class);
		Car car = context.getBean(Car.class);
		CircleB circleB = (CircleB) context.getBean("circleB");
		CircleA circleA = (CircleA) context.getBean("circleA");
		circleA.call();
		circleB.call();

		DatabaseConnectionInformation contextBean = context.getBean(DatabaseConnectionInformation.class);
		System.out.println(contextBean.toString());
	}

	@Test
	public void testConfigurationBean() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/debug/applicationContext.xml");
		System.out.println(context.getBean(ConfigurationBean.class).getClass());
		// 若在 ConfigurationBean 上添加 @Configuration 则是相同的对象,若使用@Component则表示不进行代理
		System.out.println(context.getBean(ComponentBean.class) == context.getBean(ComponentBean.class));
	}

	@Test
	public void testAnnotationApplicationContext() {
		ApplicationContext context = new AnnotationConfigApplicationContext("debug.bean");
		Car car = context.getBean(Car.class);
		System.out.println(car.toString());
	}
}
