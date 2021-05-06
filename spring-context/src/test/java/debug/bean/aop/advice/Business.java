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

package debug.bean.aop.advice;

import org.springframework.stereotype.Component;

/**
 * @author carllongj
 * 2021/5/4 20:09
 */
@Component
public class Business {

	public void doPrint(String name) {
		System.out.println("name = " + name);
	}
}
