package com.guangxunet.shop.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guangxunet.shop.base.system.Page;
import com.guangxunet.shop.base.system.PageData;
import com.guangxunet.shop.base.util.JsonResult;
import com.guangxunet.shop.business.service.information.category.CategoryService;
import com.guangxunet.shop.business.service.information.period.PeriodService;
import com.guangxunet.shop.web.controller.base.BaseController;

/** 
*@Title IndexPageController.java 
*@description APP首页控制器
*@time 2017年8月10日 下午9:32:10 
*@author GENGSHUQIANG
*@version 1.0 
**/
@Controller
@RequestMapping(value="/IndexPage")
public class IndexPageController extends BaseController{
	
	@Resource(name="categoryService")
	private CategoryService categoryService;
	@Resource(name="periodService")
	private PeriodService periodService;
	@Value("${common.website.address}")
	private String commonImagesPath;//公共图片访问路径
	/**
	 * 获取父级商品分类
	 * @return
	 */
	@RequestMapping("/getFirstCategory.screen")
	@ResponseBody
	public Object getFirstCategory() {
		JsonResult result = null;
		PageData pd = new PageData();
		pd.put("field2", null);
		Page page = new Page();
		page.setPd(pd);
		
		try {
			logger.info("----------getFirstCategory---入参---page="+page);
			List<PageData> varList = categoryService.list(page); //列出Category列表
			logger.info("----------一级商品类目---" + varList);
			result = new JsonResult(true,"获取商品分类列表成功!",varList);
		} catch (Exception e) {
			result = new JsonResult(false,"获取数据失败!");
			logger.error("---------获取父级商品分类异常--"+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据分类id获取商品期次列表
	 * 参数：categoryId 分类编号
	 * @return
	 */
	@RequestMapping("/getProductPeriodByCategoryId.screen")
	@ResponseBody
	public Object getProductPeriodByCategoryId() {
		JsonResult result = null;
		PageData pd = null;
		pd = this.getPageData();//获取请求中的参数
		
		try {
			
			if (StringUtils.isEmpty((String)pd.get("categoryId"))) {
				throw new RuntimeException("参数categoryId不能为空！");
			}
			
			logger.info("----------getFirstCategory---入参---pd="+pd);
			//列出Period列表
			List<PageData> varList = periodService.listByCategoryId(pd);
			
			//补全图片访问域名
			for (PageData pageData : varList) {
				pageData.put("littleImgUrl", commonImagesPath + pageData.get("littleImgUrl"));
			}
			
			result = new JsonResult(true,"查询成功！",varList);
		} catch (Exception e) {
			result = new JsonResult(false,e.getMessage());
			e.printStackTrace();
			logger.info("-----首页期数列表查询出现异常------"+e);
		}
		return result;
	}
	
	
	
}
