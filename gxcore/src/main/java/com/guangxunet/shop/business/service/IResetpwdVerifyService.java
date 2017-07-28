package com.guangxunet.shop.business.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.guangxunet.shop.base.system.PageData;
import com.guangxunet.shop.business.domain.ResetpwdVerify;
import com.guangxunet.shop.business.mapper.ResetpwdVerifyMapper;

/** 
*@Title IResetpwdVerifyService.java 
*@description 重置密码验证服务 
*@time 2017年7月28日 下午10:52:06 
*@author GENGSHUQIANG
*@version 1.0 
**/
public interface IResetpwdVerifyService {
	
	ResetpwdVerify selectByPrimaryKey(Integer id);
	
	/**
	 * 根据uuid查询修改记录
	 * @param uuid
	 * @return
	 */
	ResetpwdVerify selectByUUID(String uuid);

	/**
	 * 插入重置密码记录
	 * @param phoneNumber
	 * @return 
	 * @throws Exception 
	 */
	String insertResetVerify(String phoneNumber) throws Exception;

	/**
	 * 校验是否允许修改密码，是否超时
	 * @param pd
	 */
	void validateByUUID(PageData pd);
}
