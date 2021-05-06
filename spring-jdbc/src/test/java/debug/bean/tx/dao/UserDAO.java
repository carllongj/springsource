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

import debug.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author carllongj
 * 2021/5/2 23:39
 */
@Repository
public class UserDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public UserDAO(JdbcTemplate template) {
		this.jdbcTemplate = template;
	}

	public User selectUserById(Long id) {
		return jdbcTemplate.queryForObject(String.format("select id,name,age from user where id = %d", id), new BeanPropertyRowMapper<>(User.class));
	}
}
