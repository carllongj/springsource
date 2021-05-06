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

package debug.bean.tx.service;

import debug.bean.User;
import debug.bean.tx.dao.AccountDAO;
import debug.bean.tx.dao.ConsumeRecordDAO;
import debug.bean.tx.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author carllongj
 * 2021/5/2 23:40
 */
@Repository
public class AccountService {

	@Autowired
	private AccountDAO accountDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private ConsumeRecordDAO consumeRecordDAO;

	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void setConsumeRecordDAO(ConsumeRecordDAO consumeRecordDAO) {
		this.consumeRecordDAO = consumeRecordDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	@Transactional(rollbackFor = Exception.class)
	public void transaction(Long fromId, Long targetId, String reason, Integer amount) {
		this.deduce(fromId, amount);

		User user = userDAO.selectUserById(targetId);
		int i = 10 / 0;

		this.addAmount(targetId, amount);
		consumeRecordDAO.addConsumeRecord(fromId, targetId, reason, amount);
	}

	@Transactional(rollbackFor = Exception.class)
	public void deduce(Long id, Integer amount) {
		accountDAO.deduce(id, amount);
	}

	@Transactional(rollbackFor = Exception.class)
	public void addAmount(Long id, Integer amount) {
		accountDAO.addAmount(id, amount);
	}
}
