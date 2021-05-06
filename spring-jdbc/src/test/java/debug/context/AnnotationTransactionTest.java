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

import debug.bean.tx.dao.AccountDAO;
import debug.bean.tx.dao.ConsumeRecordDAO;
import debug.bean.tx.dao.UserDAO;
import debug.bean.tx.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.lang.reflect.Field;

/**
 * @author carllongj
 * 2021/5/3 18:46
 */
public class AnnotationTransactionTest {
	private ApplicationContext context;
	private DataSource dataSource;
	private DataSourceTransactionManager manager;
	private JdbcTemplate jdbcTemplate;
	private AccountDAO accountDAO;
	private UserDAO userDAO;
	private ConsumeRecordDAO consumeRecordDAO;
	private AccountService accountService;

	@BeforeEach
	public void init() {
		context = new ClassPathXmlApplicationContext("classpath:/debug/applicationContext-annotation-tx.xml");
		dataSource = context.getBean(DataSource.class);
		manager = context.getBean(DataSourceTransactionManager.class);
		jdbcTemplate = context.getBean(JdbcTemplate.class);
		accountDAO = context.getBean(AccountDAO.class);
		userDAO = context.getBean(UserDAO.class);
		consumeRecordDAO = context.getBean(ConsumeRecordDAO.class);
		accountService = context.getBean(AccountService.class);
	}


	@Test
	public void testWithProxyTransaction() {
		accountService.transaction(2L, 1L, "借款2000", 2000);
	}

	/**
	 * 报错没有 userDAO属性,由于该对象为代理对象,故其中不存在该属性
	 * @throws Exception 会抛出异常
	 */
	@Test
	public void testProxyObject() throws Exception {
		Field userDAO = accountService.getClass().getDeclaredField("userDAO");
		userDAO.setAccessible(true);
		Object o = userDAO.get(accountService);
		System.out.println(o);
	}
}
