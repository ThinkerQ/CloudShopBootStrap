package com.guangxunet.shop.business.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guangxunet.shop.base.util.LoggerUtil;
import com.guangxunet.shop.business.domain.Account;
import com.guangxunet.shop.business.mapper.AccountMapper;
import com.guangxunet.shop.business.service.IAccountService;

/** 
*@Title AccountServiceImpl.java 
*@description  用户虚拟账户相关服务
*@time 2017年9月16日 上午11:42:35 
*@author GENGSHUQIANG
*@version 1.0 
**/
@Service("iAccountService")
public class AccountServiceImpl implements IAccountService {
	@Autowired
	private AccountMapper accountMapper;
	
	/**
     * 添加账户信息
     * @param record
     * @return
     */
	@Override
	public int insert(Account record) {
		return accountMapper.insert(record);
	}

	/**
     * 查询账户信息
     * @param id
     * @return
     */
	@Override
	public Account selectByPrimaryKey(Long id) {
		return accountMapper.selectByPrimaryKey(id);
	}

	/**
     * 更新用户账户信息
     * @param record
     * @return
     */
	@Override
	public int updateByPrimaryKey(Account record) {
		return accountMapper.updateByPrimaryKey(record);
	}

	/**
	 * 根据userId更新账户余额
	 */
	@Override
	public int updateAmountByUserId(Account account) {
		//查询用户之前的账户余额
		LoggerUtil.info("------------------入参--account="+account);
		Account oldAmount = accountMapper.selectByUserId(account);
		LoggerUtil.info("------------------原有账户信息--oldAmount="+oldAmount);
		BigDecimal usableAmount = oldAmount.getUsableAmount();
		Integer version = oldAmount.getVersion();
		
		//添加要充值的金额
		BigDecimal newAmount = usableAmount.add(account.getUsableAmount());
		account.setUsableAmount(newAmount);
		account.setUpdateDate(new Date());
		account.setVersion(version);
		//添加乐观锁机制
		int count = accountMapper.updateAmountByUserId(account);
		
		if (count <=0) {//与前面查询出来的进行比较
			throw new RuntimeException("乐观锁失败！请刷新再试");
		}
		
		return count;
	}

}
