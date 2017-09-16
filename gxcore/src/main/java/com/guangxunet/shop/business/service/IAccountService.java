package com.guangxunet.shop.business.service;

import java.util.List;

import com.guangxunet.shop.business.domain.Account;

/** 
*@Title IAccountService.java 
*@description 用户虚拟账户相关服务
*@time 2017年9月16日 上午11:42:01 
*@author GENGSHUQIANG
*@version 1.0 
**/
public interface IAccountService {
    /**
     * 添加账户信息
     * @param record
     * @return
     */
    int insert(Account record);

    /**
     * 查询账户信息
     * @param id
     * @return
     */
    Account selectByPrimaryKey(Long id);

    /**
     * 更新用户账户信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(Account record);

    /**
     * 根据用户id充值
     * @param account
     */
	int updateAmountByUserId(Account account);
}
