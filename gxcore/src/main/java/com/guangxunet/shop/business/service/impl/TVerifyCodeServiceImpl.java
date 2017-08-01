package com.guangxunet.shop.business.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guangxunet.shop.business.domain.VerifyCode;
import com.guangxunet.shop.business.mapper.VerifyCodeMapper;
import com.guangxunet.shop.business.service.ITVerifyCodeService;

/** 
*@Title TVerifyCodeServiceImpl.java 
*@description 短信验证码记录表相关服务
*@time 2017年7月31日 下午9:46:29 
*@author GENGSHUQIANG
*@version 1.0 
**/
@Service("tVerifyCodeService")
public class TVerifyCodeServiceImpl implements ITVerifyCodeService {
	@Autowired
    private VerifyCodeMapper verifyCodeMapper;
	
	@Override
	public int insert(VerifyCode record) {
		return verifyCodeMapper.insert(record);
	}

	@Override
	public VerifyCode selectByPrimaryKey(Integer id) {
		return verifyCodeMapper.selectByPrimaryKey(id);
	}

	@Override
	public VerifyCode selectByPhoneNumber(Map<String, Object> params) {
		return verifyCodeMapper.selectByPhoneNumber(params);
	}

	@Override
	public VerifyCode selectCurrentCodeByPhoneNumber(String phoneNumber) {
		return verifyCodeMapper.selectCurrentCodeByPhoneNumber(phoneNumber);
	}

}
