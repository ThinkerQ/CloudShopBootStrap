package com.guangxunet.shop.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guangxunet.shop.base.system.PageData;
import com.guangxunet.shop.base.util.JsonResult;
import com.guangxunet.shop.base.util.LoggerUtil;
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
	@Value("${common.website.address}")
	private String commonImagesPath;//公共图片访问路径
	
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
			pd.put("keyword", keyword);
			//列出Period列表
			List<PageData> varList = periodService.listByOrder(pd);
			
			//补全图片访问域名
			for (PageData pageData : varList) {
				pageData.put("littleImgUrl", commonImagesPath + pageData.get("littleImgUrl"));
			}
			
			result = new JsonResult(true,"查询成功！",varList);
		} catch (Exception e) {
			result = new JsonResult(false,"查询异常！");
			e.printStackTrace();
			logger.info("-----首页期数列表查询出现异常------"+e);
		}
		
		return result;
	}
	
	/**
	 * 通过id查询期数详情
	 * @return
	 */
	@RequestMapping("/queryPeriodDetail.screen")
	@ResponseBody
	public Object queryPeriodDetail(){
		logBefore(logger, "通过id查询期数详情");
		JsonResult result = null;
		PageData pd = this.getPageData();
		String period_ID = pd.getString("period_ID");//排序关键字
		try {
			pd.put("period_ID", period_ID);
			PageData pageData = periodService.queryPeriodById(pd);
			
			//补全图片访问地址
			String littleImgUrl = commonImagesPath + pageData.getString("littleImgUrl");
			String bigImgUrl = commonImagesPath + pageData.getString("bigImgUrl");
			pageData.put("littleImgUrl", littleImgUrl);
			pageData.put("bigImgUrl", bigImgUrl);
			LoggerUtil.info("==================获取商品详情=====结果==pageData=" + pageData);
			
			result = new JsonResult(true,"查询queryPeriodById成功！",pageData);
		} catch (Exception e) {
			result = new JsonResult(false,"查询queryPeriodById异常！");
			logger.error("-----查询期数详情queryPeriodById异常------"+e);
		}
		
		return result;
	}
	
	/**
	 * 通过商品id查询历史期数
	 * @return
	 */
	@RequestMapping("/queryHistoryPeriods.screen")
	@ResponseBody
	public Object queryHistoryPeriods(){
		logBefore(logger, "通过商品id查询历史期数");
		JsonResult result = null;
		PageData pd = this.getPageData();
		String productId = pd.getString("productId");//排序关键字
		try {
			pd.put("productId", productId);
			List<PageData> pageData = periodService.queryPeriodsByProductId(pd);
			result = new JsonResult(true,"查询历史期数queryHistoryPeriods成功！",pageData);
		} catch (Exception e) {
			result = new JsonResult(false,"查询历史期数queryHistoryPeriods异常！");
			logger.error("-----查询历史期数queryHistoryPeriods异常------"+e);
		}
		return result;
	}
	
	/**
	 * 获取获奖的用户和商品信息列表
	 * @return
	 */
	@RequestMapping("/queryPrizedUserAndProduct.screen")
	@ResponseBody
	public Object queryPrizedUserAndProduct(){
		logBefore(logger, "获取获奖的用户和商品信息列表");
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
			logger.error("-----查询获奖的用户和商品信息异常------"+e);
		}
		return result;
	}
}
