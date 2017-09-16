package com.guangxunet.shop.business.mapper;

import com.guangxunet.shop.business.domain.Account;
import java.util.List;

public interface AccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Account record);

    Account selectByPrimaryKey(Long id);

    List<Account> selectAll();

    int updateByPrimaryKey(Account record);

    /**
     * 更新账户余额
     * @param account
     * @return
     */
	int updateAmountByUserId(Account account);

	/**
	 * 根据userId查询账户信息
	 * @param account
	 * @return
	 */
	Account selectByUserId(Account account);
}