package com.guangxunet.shop.web.controller;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guangxunet.shop.base.system.PageData;
import com.guangxunet.shop.base.util.JsonResult;
import com.guangxunet.shop.base.util.LoggerUtil;
import com.guangxunet.shop.business.domain.Account;
import com.guangxunet.shop.business.service.IAccountService;
import com.guangxunet.shop.web.controller.base.BaseController;

/** 
*@Title AccountController.java 
*@description 账户控制器
*@time 2017年9月16日 下午1:44:50 
*@author GENGSHUQIANG
*@version 1.0 
**/
@Controller
@RequestMapping("/Account")
public class AccountController extends BaseController{
	@Autowired
	private IAccountService accountService;
	
	/**
	 * 账户充值
	 * @return
	 */
	@RequestMapping("/recharge.screen")
    @ResponseBody
    public Object recharge() {
		JsonResult result = null;
		//参数非空校验
		PageData pd = null;
		pd = this.getPageData();
		LoggerUtil.info("-------------pd--------"+pd);
		try {
			//参数非空校验
			this.validateParams(pd);
			
			//TODO 交易密码校验
			
			Account account = new Account();
			account.setUserId(Long.valueOf(pd.getString("userId")));
			account.setUsableAmount(new BigDecimal(pd.getString("addAmount")));
			accountService.updateAmountByUserId(account);

    		LoggerUtil.info("-------------recharge---success------");
			result = new JsonResult(true, "充值成功！", null);
		} catch (Exception e) {
			result = new JsonResult(false,"充值异常："+e.getMessage());
			e.printStackTrace();
		}
		return result;
    }

	/**
	 * 充值时参数非空校验
	 * @param pd
	 */
	private void validateParams(PageData pd) throws Exception{
		String userId = pd.getString("userId");//用户id
		String addAmount = pd.getString("addAmount");//充值金额
		
		if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(addAmount)) {
			throw new RuntimeException("用户id和充值金额为空！");
		}
	}
}
