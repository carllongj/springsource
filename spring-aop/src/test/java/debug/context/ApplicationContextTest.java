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

import debug.bean.advice.AopAdvisor;
import debug.bean.business.Business;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.autoproxy.AspectJAwareAdvisorAutoProxyCreator;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author carllongj
 * 2021/5/4 12:21
 */
public class ApplicationContextTest {
	DefaultListableBeanFactory beanFactory;
	XmlBeanDefinitionReader definitionReader;

	@BeforeEach
	public void init() {
		beanFactory = new DefaultListableBeanFactory();
		definitionReader = new XmlBeanDefinitionReader(beanFactory);
		//definitionReader.loadBeanDefinitions("classpath:/debug/applicationContext-aop.xml");
		definitionReader.loadBeanDefinitions("classpath:/debug/applicationContext-annotation.xml");
	}

	@Test
	public void loadAnnotationConfigFromXml() {
		beanFactory.addBeanPostProcessor((BeanPostProcessor) beanFactory.getBean("annotationAwareAspectJAutoProxyCreator"));
		// beanFactory.addBeanPostProcessor((BeanPostProcessor) beanFactory.getBean("aspectJAwareAdvisorAutoProxyCreator"));
		Business bean = (Business) beanFactory.getBean("business");
		bean.doPrint("hello");
	}

	@Test
	public void loadConfigurationFromXml() {
		// 先创建所需要的 BeanPostProcessor
		AspectJAwareAdvisorAutoProxyCreator proxyCreator = beanFactory.getBean(AspectJAwareAdvisorAutoProxyCreator.class);
		beanFactory.addBeanPostProcessor(proxyCreator);

		// 获取执行得到代理的bean
		AopAdvisor advisor = beanFactory.getBean(AopAdvisor.class);
		advisor.print();
	}

	@Test
	public void serializeProxy(){
	}
}
