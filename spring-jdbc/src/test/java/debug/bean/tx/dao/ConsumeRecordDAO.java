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
 * 2021/5/2 23:46
 */

@Repository
public class ConsumeRecordDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public ConsumeRecordDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void addConsumeRecord(Long fromId, Long targetId, String reason, Integer amount) {
		jdbcTemplate.update(String.format("insert into consume_record(origin_user_id,target_user_id" +
				",consume_reason,amount) values (%d,%d,'%s',%d)", fromId, targetId, reason, amount));
	}
}
