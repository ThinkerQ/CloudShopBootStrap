package com.guangxunet.shop.business.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guangxunet.shop.base.domain.Logininfo;
import com.guangxunet.shop.base.mapper.LogininfoMapper;
import com.guangxunet.shop.base.system.DateUtil;
import com.guangxunet.shop.base.system.PageData;
import com.guangxunet.shop.base.util.BidConst;
import com.guangxunet.shop.base.util.LoggerUtil;
import com.guangxunet.shop.base.util.MD5;
import com.guangxunet.shop.business.domain.ResetpwdVerify;
import com.guangxunet.shop.business.mapper.ResetpwdVerifyMapper;
import com.guangxunet.shop.business.service.IResetpwdVerifyService;

import shaded.org.apache.commons.lang3.StringUtils;

/** 
*@Title ResetpwdVerifyServiceImpl.java 
*@description TODO 
*@time 2017年7月28日 下午10:55:56 
*@author GENGSHUQIANG
*@version 1.0 
**/
@Service("resetpwdVerifyService")
public class ResetpwdVerifyServiceImpl implements IResetpwdVerifyService {
	@Autowired
    private ResetpwdVerifyMapper resetpwdVerifyMapper;
	@Autowired
    private LogininfoMapper logininfoMapper;
	@Override
	public ResetpwdVerify selectByPrimaryKey(Integer id) {
		return null;
	}

	@Override
	public ResetpwdVerify selectByUUID(String uuid) {
		return resetpwdVerifyMapper.selectByUUID(uuid);
	}

	/**
	 * 插入重置密码记录
	 */
	@Override
	public String insertResetVerify(String phoneNumber) throws Exception{
		String uuid = UUID.randomUUID().toString();
		//1.根据手机号查询用户id,发送时间，uuid
		Logininfo login = logininfoMapper.getUserByMobile(phoneNumber);
		LoggerUtil.info("------login-"+login);
		if (login!=null) {
			/*Map<String,Object> params = new HashMap<String,Object>();
			params.put("id", login.getId());
			params.put("uuid", uuid);
			params.put("sendTime", new Date());*/
			ResetpwdVerify resetpwdVerify = new ResetpwdVerify();
			resetpwdVerify.setUserId(login.getId());
			resetpwdVerify.setSendTime(new Date());
			resetpwdVerify.setUuid(uuid);
			resetpwdVerifyMapper.insert(resetpwdVerify);
		}else{
			throw new RuntimeException("手机号有误！");
		}
		return uuid;
	}

	@Override
	public void validateByUUID(PageData pd) {
		String uuid= pd.getString("uuid");
		String phoneNumber= pd.getString("phoneNumber");
		String newPassword= pd.getString("newPassword");
		
		//验证手机号
		if (StringUtils.isEmpty(phoneNumber)) {
			throw new RuntimeException("手机号为空！");
		}
		
		if (StringUtils.isEmpty(newPassword)) {
    		throw new RuntimeException("新密码为空！");
		}
		
		if (StringUtils.isEmpty(uuid)) {
			throw new RuntimeException("校验识别码为空！");
		}
		
		//手机号是否为已注册用户
		boolean numberExist = logininfoMapper.countUserByMobile(phoneNumber)>0;
		
		if (!numberExist) {
    		throw new RuntimeException("非注册用户！");
		}
		
		ResetpwdVerify rv = resetpwdVerifyMapper.selectByUUID(uuid);
		
		//如果有（已验证过可修改密码），且在有效期之内
		if (rv!=null && DateUtil.getBetweenSecond(new Date(),rv.getSendTime()) <=  BidConst.VERIFY_EMAIL_DAY *60){
		    //修改密码
			 logininfoMapper.resetPassword(phoneNumber,MD5.encode(newPassword));
		}else{
			throw new RuntimeException("修改链接有误或已超时，请重试！");
		}
		
	}

}
