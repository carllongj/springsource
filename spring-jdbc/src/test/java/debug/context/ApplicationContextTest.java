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
	 * ?????????????????????,????????????
	 */
	@Test
	public void testTransferBalanceWithoutTransaction() {
		accountService.transaction(2L, 1L, "?????? 400", 400);
	}

	/**
	 * ???????????????????????????
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
	 * ???????????????????????????????????????
	 *
	 */
	@Test
	public void testPropagationRequired() throws SQLException {
		/* ----------------- ???????????????------------*/
		DefaultTransactionDefinition firstTransactionDefinition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus firstStatus = manager.getTransaction(firstTransactionDefinition);
		accountService.deduce(2L, 2000);
		// ????????????????????????

		// ????????????????????????,?????????????????? PROPAGATION_REQUIRED ??????
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);
		// ??????????????????????????????,?????????????????????????????????,????????????????????????????????????????????????
		TransactionStatus transactionStatus = manager.getTransaction(definition);

		try {
			// ??????????????????????????????,???????????????????????????
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
