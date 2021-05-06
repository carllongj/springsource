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

package debug.bean.tx;

import debug.bean.JdbcUrlParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author carllongj
 * 2021/5/3 18:49
 */
@Configuration
@EnableTransactionManagement
public class TransactionManagerBean {

	@Autowired
	private JdbcUrlParams jdbcUrlParams;

	@Bean
	public DataSource createDatasource() {
		return new DriverManagerDataSource(jdbcUrlParams.getUrl(), jdbcUrlParams.getUsername(), jdbcUrlParams.getPassword());
	}

	@Bean
	public TransactionManager createTransactionManager() {
		return new DataSourceTransactionManager(createDatasource());
	}

	@Bean
	public JdbcTemplate createJdbcTemplate() {
		return new JdbcTemplate(createDatasource());
	}
}
