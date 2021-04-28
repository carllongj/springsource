package debug.context;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.testfixture.servlet.MockHttpServletRequest;
import org.springframework.web.testfixture.servlet.MockHttpServletResponse;
import org.springframework.web.testfixture.servlet.MockServletConfig;
import org.springframework.web.testfixture.servlet.MockServletContext;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author longjie
 * 2021/4/28
 */
public class XmlWebServletContextTest {

	private Servlet servlet;

	@BeforeEach
	public void init() throws Exception {
		XmlWebApplicationContext context = new XmlWebApplicationContext();
		context.setConfigLocation("classpath:/debug/applicationContext-mvc.xml");
		context.refresh();
		Object controller = context.getBean("testController");
		System.out.println(controller);
		MockServletContext mockServletContext = new MockServletContext();
		MockServletConfig config = new MockServletConfig(mockServletContext);

		DispatcherServlet servlet = new DispatcherServlet(context);
		// 初始化加载相关的bean,Dispatcher 中的onRefresh 方法不是容器的 onRefresh.
		servlet.init(config);
		// 此处则可以接受所有的请求处理
		this.servlet = servlet;
	}

	@Test
	public void test() throws ServletException, IOException {
		servlet.service(new MockHttpServletRequest("GET", "/test/fun"), new MockHttpServletResponse());
	}
}
