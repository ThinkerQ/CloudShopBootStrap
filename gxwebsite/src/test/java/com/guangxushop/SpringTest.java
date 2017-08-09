package com.guangxushop;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.guangxunet.shop.base.system.PageData;
import com.guangxunet.shop.base.util.LoggerUtil;
import com.guangxunet.shop.base.util.UuidUtil;
import com.guangxunet.shop.business.domain.VerifyCode;
import com.guangxunet.shop.business.service.ITVerifyCodeService;
import com.guangxunet.shop.business.service.information.period.PeriodService;

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
	@Autowired
	private PeriodService periodService;
	
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
	
	/**
	 * 期数列表
	 * @throws Exception
	 */
	@Test
	public void testPeriod() throws Exception {
		PageData pd = new PageData();
//		pd.put("keyword", "hostEst");//最热
		pd.put("keyword", "newEst");//最新
		//列出Period列表
		List<PageData> varList = periodService.listByOrder(pd);
		LoggerUtil.info("----------------varList="+varList);
	}
	
	/**
	 * 期数列表
	 * @throws Exception
	 */
	@Test
	public void testPeriodById() throws Exception {
		PageData pd = new PageData();
		pd.put("productId", "1");//最新
		//列出Period列表
		List<PageData> pageData = periodService.queryPeriodsByProductId(pd);
		LoggerUtil.info("----------------varList="+pageData);
	}
	
	
	
}
