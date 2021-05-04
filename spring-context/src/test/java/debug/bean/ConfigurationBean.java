package debug.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author longjie
 * 2021/4/30
 */
@Component
public class ConfigurationBean {

	@Bean
	public ComponentBean createComponentBean() {
		return new ComponentBean(innerBean());
	}

	/**
	 * 使用 @Configuration 时由于此处被代理了,获取该对象的实例将由Spring来进行控制.
	 * 当该方法(实际上为代理对象)被调用时,Spring 将 单例池中查找该InnerBean对象.
	 * 若该对象不存在,则进行创建,若不存在,将会调用到真实对象的方法,即当前方法.
	 *
	 * 由于该方法被代理,则无论通过何种方式
	 * 1. 通过 Spring 容器获取该对象.
	 * 2. 通过调用 代理对象的 代理方法,即通过Spring获取 ConfigurationBean,并且调用其 innerBean 方法
	 * 这两种方式获取的对象都将会是相同的实例
	 *
	 * @return 返回创建的实例
	 */
	@Bean
	public InnerBean innerBean() {
		return new InnerBean();
	}

}
