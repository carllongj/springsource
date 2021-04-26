package debug.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author longjie
 * 2021/4/26
 */
@Component
public class CircleA {

	@Autowired
	private CircleB b;

	@Autowired
	private Car car;

	public void call() {
		System.out.println("b = " + b);
	}
}
