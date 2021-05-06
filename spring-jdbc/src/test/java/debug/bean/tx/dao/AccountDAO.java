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

package debug.bean.tx.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author carllongj
 * 2021/5/2 23:33
 */
@Repository
public class AccountDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public AccountDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void deduce(Long id, Integer amount) {
		jdbcTemplate.update(String.format("update account set account_balance = account_balance - %d where id = %d", amount, id));
	}

	public void addAmount(Long id, Integer amount) {
		jdbcTemplate.update(String.format("update account set account_balance = account_balance + %d where id = %d", amount, id));
	}
}
