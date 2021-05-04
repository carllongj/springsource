package debug.context;

import debug.bean.*;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
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
		System.out.println(context.getBean(ComponentBean.class).getInnerBean() == context.getBean(InnerBean.class));
		// 由于方法被代理,Spring 保证了调用方法也会是相同的实例
		System.out.println(context.getBean(InnerBean.class) == context.getBean(ConfigurationBean.class).innerBean());
	}

	@Test
	public void testAnnotationApplicationContext() {
		ApplicationContext context = new AnnotationConfigApplicationContext("debug.bean");
		Car car = context.getBean(Car.class);
		System.out.println(car.toString());
	}
}
