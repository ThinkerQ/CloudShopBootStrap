package com.guangxushop;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.guangxunet.shop.base.util.UuidUtil;
import com.guangxunet.shop.business.domain.VerifyCode;
import com.guangxunet.shop.business.service.ITVerifyCodeService;

/** 
*@Title SpringTest.java 
*@description TODO 
*@time 2017年7月31日 下午9:15:08 
*@author GENGSHUQIANG
*@version 1.0 
**/
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("classpath:applicationContext.xml") 
public class SpringTest {
	@Autowired
	private ITVerifyCodeService tVerifyCodeService;
	
	@Test
	public void VerifyCode(){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("phoneNumber", "18277778888");
		params.put("code", "838023");
		VerifyCode verifyCode = tVerifyCodeService.selectByPhoneNumber(params);
		System.out.println("-------------verifyCode="+verifyCode);
	}
	
	@Test
	public void VerifyCodeGetbyId(){
		VerifyCode verifyCode = tVerifyCodeService.selectByPrimaryKey(11);
		System.out.println("-------------verifyCode="+verifyCode);
	}
	
	@Test
	public void random(){
		String randomVcode = UuidUtil.createRandomVcode();
		System.out.println("-------------randomVcode="+randomVcode);
	}
	
	
	
}
