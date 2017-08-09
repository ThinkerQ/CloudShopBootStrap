package com.guangxunet.shop.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guangxunet.shop.base.system.Page;
import com.guangxunet.shop.base.system.PageData;
import com.guangxunet.shop.base.util.JsonResult;
import com.guangxunet.shop.business.service.information.period.PeriodService;
import com.guangxunet.shop.business.util.CollectionUtil;
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
	 * 首页期数列表
	 * 按照最热(hostEst)、最快(fastEst)、最新(newEst)、高价(hightPriceEst)、低价(lowPriceEst)排序
	 * 入参：String keyword
	 * @return
	 */
	@RequestMapping("/listbyOrder.screen")
	@ResponseBody
	public Object listbyOrder(){
		logBefore(logger, "--按关键字排序--期数列表--");
		JsonResult result = null;
		PageData pd = null;
		pd = this.getPageData();//获取请求中的参数
		String keyword = pd.getString("keyword");//排序关键字
		
		try {
			//列出Period列表
			List<PageData> varList = periodService.listByOrder(pd);
			result = new JsonResult(true,"查询成功！",varList);
		} catch (Exception e) {
			result = new JsonResult(false,"查询异常！");
			e.printStackTrace();
			logger.info("-----首页期数列表查询出现异常------"+e);
		}
		
		return result;
	}
	
}
