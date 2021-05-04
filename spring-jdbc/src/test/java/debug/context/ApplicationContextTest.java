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

import debug.bean.JdbcUrlParams;
import debug.bean.User;
import debug.bean.tx.dao.AccountDAO;
import debug.bean.tx.dao.ConsumeRecordDAO;
import debug.bean.tx.dao.UserDAO;
import debug.bean.tx.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import javax.transaction.Transaction;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

/**
 * @author carllongj
 * 2021/5/2 21:22
 */
public class ApplicationContextTest {

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
		context = new ClassPathXmlApplicationContext("classpath:/debug/applicationContext-tx.xml");
		JdbcUrlParams urlParams = context.getBean(JdbcUrlParams.class);
		dataSource = new DriverManagerDataSource(urlParams.getUrl(), urlParams.getUsername(), urlParams.getPassword());
		manager = new DataSourceTransactionManager(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
		accountDAO = new AccountDAO(jdbcTemplate);
		userDAO = new UserDAO(jdbcTemplate);
		consumeRecordDAO = new ConsumeRecordDAO(jdbcTemplate);

		accountService = new AccountService();
		accountService.setAccountDAO(accountDAO);
		accountService.setUserDAO(userDAO);
		accountService.setConsumeRecordDAO(consumeRecordDAO);
	}

	@Test
	public void testDatasourceConnection() {

		User user = jdbcTemplate.queryForObject("select * from user where id = 1", new BeanPropertyRowMapper<>(User.class));
		System.out.println(user);
	}

	/**
	 * 测试未开启事务,正常逻辑
	 */
	@Test
	public void testTransferBalanceWithoutTransaction() {
		accountService.transaction(2L, 1L, "转账 400", 400);
	}

	/**
	 * 测试带有事务的执行
	 */
	@Test
	public void testProgrammingTransferBalance() {
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus transactionStatus = manager.getTransaction(definition);
		try {
			accountService.deduce(2L, 2000);
			int i = 1 / 0;
			accountService.addAmount(1L, 2000);
			manager.commit(transactionStatus);
		} catch (Exception e) {
			manager.rollback(transactionStatus);
		} catch (Throwable e) {
			manager.rollback(transactionStatus);
		}
	}

	/**
	 * 测试传播特性为必须要事务的
	 *
	 */
	@Test
	public void testPropagationRequired() throws SQLException {
		/* ----------------- 第一个事务------------*/
		DefaultTransactionDefinition firstTransactionDefinition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus firstStatus = manager.getTransaction(firstTransactionDefinition);
		accountService.deduce(2L, 2000);
		// 此处为第一个事务

		// 由于之前没有事务,此处开启新的 PROPAGATION_REQUIRED 事务
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);
		// 当此处的代码被执行后,事务将进入开启新的事务,后续的所有操作将在一个事务中进行
		TransactionStatus transactionStatus = manager.getTransaction(definition);

		try {
			// 此处的执行将会被回滚,由于此处定义了事务
			accountService.addAmount(1L, 2000);
			int i = 1 / 0;
		} catch (Exception e) {
			manager.rollback(transactionStatus);
		} catch (Throwable e) {
			manager.rollback(transactionStatus);
		}

		manager.commit(firstStatus);

	}

	@Test
	public void jdbcTest() throws Exception {
		Connection connection = dataSource.getConnection();
		Savepoint savepoint = connection.setSavepoint("identifier");
		System.out.println(savepoint.getSavepointName());
	}
}
