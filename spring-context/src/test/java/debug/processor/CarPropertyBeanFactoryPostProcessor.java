package debug.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author longjie
 * 2021/4/20
 */
public class CarPropertyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		BeanDefinition beanDefinition = beanFactory.getBeanDefinition("car");
		beanDefinition.getPropertyValues().add("id", "1");
		beanDefinition.getPropertyValues().add("brand", "benz");
		beanDefinition.getPropertyValues().add("logo", "sq");
	}
}
