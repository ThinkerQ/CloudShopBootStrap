package com.guangxushop;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.guangxunet.shop.base.system.Page;
import com.guangxunet.shop.base.system.PageData;
import com.guangxunet.shop.base.util.JsonResult;
import com.guangxunet.shop.base.util.LoggerUtil;
import com.guangxunet.shop.base.util.UuidUtil;
import com.guangxunet.shop.business.domain.VerifyCode;
import com.guangxunet.shop.business.service.ITVerifyCodeService;
import com.guangxunet.shop.business.service.information.category.CategoryService;
import com.guangxunet.shop.business.service.information.period.PeriodService;
import com.guangxunet.shop.business.util.CollectionUtil;

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
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * 获奖用户列表
	 * @throws Exception
	 */
	@Test
	public void testPrizedUser() throws Exception {
		JsonResult result = null;
		try {
			List<PageData> prizedUserList = periodService.queryPrizedUserAndProduct();
			
			if (CollectionUtil.isEmpty(prizedUserList)) {
				result = new JsonResult(false,"花一元钱就有机会赢大奖,赶紧云购吧！",prizedUserList);
			}else{
				//拼接用户获奖信息
				List<String> strList = new ArrayList<String>();
				for (PageData pageData : prizedUserList) {
					StringBuffer sb = new StringBuffer();
					sb.append("恭喜:");
					sb.append(pageData.getString("userName"));
					sb.append("获得");
					sb.append(pageData.getString("firstName"));
					strList.add(sb.toString());
				}
				result = new JsonResult(true,"查询获奖的用户和商品信息成功！",strList);
			}
		} catch (Exception e) {
			result = new JsonResult(false,"查询获奖用户和商品信息异常！");
		}
		System.out.println("--------------result="+result);
	}
	
	/**
	 * 根据分类id获取商品期次列表
	 */
	@Test
	public void testGetProductPeriodByCategoryId() throws Exception {
		JsonResult result = null;
		PageData pd = new PageData();
		pd.put("productId", "47");
		
		try {
			LoggerUtil.info("----------getFirstCategory---入参---pd="+pd);
			//列出Period列表
			List<PageData> varList = periodService.listByCategoryId(pd);
			result = new JsonResult(true,"查询成功！",varList);
		} catch (Exception e) {
			result = new JsonResult(false,"查询异常！");
			e.printStackTrace();
			LoggerUtil.info("-----首页期数列表查询出现异常------"+e);
		}
		LoggerUtil.info("-----testGetProductPeriodByCategoryId------result="+result);
		
	}
	
	/*
	 * 获取商品分类列表测试
	 */
	@Test
	public void testCateGory() throws Exception {
		JsonResult result = null;
		PageData pd = new PageData();
		pd.put("field2", null);
		Page page = new Page();
		page.setPd(pd);
		
		try {
			LoggerUtil.info("----------getFirstCategory---入参---page="+page);
			List<PageData> varList = categoryService.list(page); //列出Category列表
			LoggerUtil.info("----------一级商品类目---" + varList);
			result = new JsonResult(true,"获取商品分类列表成功!",varList);
		} catch (Exception e) {
			result = new JsonResult(false,"获取数据失败!");
			LoggerUtil.error("---------获取父级商品分类异常--"+e.getMessage());
			e.printStackTrace();
		}
		LoggerUtil.info("---result--"+result);
	}
	
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
