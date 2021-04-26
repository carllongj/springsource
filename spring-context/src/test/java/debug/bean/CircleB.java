package debug.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author longjie
 * 2021/4/26
 */
@Component
public class CircleB {

	@Autowired
	private CircleA a;

	public void call() {
		System.out.println("a = " + a);
	}
}
