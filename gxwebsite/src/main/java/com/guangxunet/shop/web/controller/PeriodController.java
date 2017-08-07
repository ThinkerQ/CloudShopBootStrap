package com.guangxunet.shop.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guangxunet.shop.base.system.Page;
import com.guangxunet.shop.base.system.PageData;
import com.guangxunet.shop.business.service.information.period.PeriodService;
import com.guangxunet.shop.web.controller.base.BaseController;

/** 
*@Title PeriodController.java 
*@description 云购期数控制器(前端)
*@time 2017年7月25日 下午10:29:30 
*@author GENGSHUQIANG
*@version 1.0 
**/
@Controller
@RequestMapping("Period")
public class PeriodController extends BaseController{
	@Resource(name="periodService")
	private PeriodService periodService;
	
	/**
	 * 列表
	 * @return
	 */
	public Object list(Page page){
		logBefore(logger, "--按关键字排序--期数列表--");
		PageData pd = null;
		pd = this.getPageData();//获取请求中的参数
		
		
		return null;
	}
	
}
