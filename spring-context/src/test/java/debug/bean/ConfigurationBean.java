package debug.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author longjie
 * 2021/4/30
 */
@Configuration
public class ConfigurationBean {

	@Autowired
	private InnerBean innerBean;

	@Bean
	public ComponentBean createComponentBean() {
		return new ComponentBean(innerBean);
	}
}
