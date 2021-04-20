package debug.context;

import debug.bean.Car;
import debug.bean.Person;
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
		System.out.println(person.toString());
		Car car = context.getBean(Car.class);
		System.out.println(car.toString());
	}

	@Test
	public void testAnnotationApplicationContext() {
		ApplicationContext context = new AnnotationConfigApplicationContext("debug.bean");
		Car car = context.getBean(Car.class);
		System.out.println(car.toString());
	}
}
