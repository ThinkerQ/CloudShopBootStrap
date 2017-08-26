package com.guangxunet.shop.web.controller;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guangxunet.shop.base.domain.Logininfo;
import com.guangxunet.shop.base.system.Page;
import com.guangxunet.shop.base.system.PageData;
import com.guangxunet.shop.base.util.Const;
import com.guangxunet.shop.base.util.JsonResult;
import com.guangxunet.shop.base.util.StringUtils;
import com.guangxunet.shop.base.util.UserContext;
import com.guangxunet.shop.business.service.information.shoppingcart.ShoppingCartService;
import com.guangxunet.shop.business.util.AppUtil;
import com.guangxunet.shop.web.controller.base.BaseController;

/** 
 * 类名称：ShoppingCartController
 * 创建人：FH 
 * 创建时间：2017-08-16
 */
@Controller
@RequestMapping(value="/ShoppingCart")
public class ShoppingCartController extends BaseController {
	
	@Autowired
	private ShoppingCartService shoppingcartService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	@ResponseBody
	public Object save() throws Exception{
		logBefore(logger, "新增ShoppingCart");
		JsonResult result = null;
		PageData pd = null;
		try{
			pd = this.getPageData();
			String periodsId = pd.getString("periodsId");
			String participantCount = pd.getString("participantCount");
			Logininfo current = UserContext.getCurrent();
			pd.put("userId", current.getId());	//用户ID
			pd.put("periodsId", periodsId);	//期数ID
			pd.put("participantCount", participantCount);	//参与人次
			pd.put("createTime", new Date());	//加入时间
			shoppingcartService.save(pd);
			result = new JsonResult(true,"购物车列表新增成功！");
		} catch (Exception e) {
			result = new JsonResult(false,"购物车列表新增异常！");
			logger.info("-----购物车列表新增出现异常------"+e);
		}
		return result;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public Object delete(PrintWriter out){
		logBefore(logger, "删除ShoppingCart");
		JsonResult result = null;
		PageData pd = null;
		try{
			pd = this.getPageData();
			Logininfo current = UserContext.getCurrent();
			pd.put("userId", current.getId());	//用户ID
			String periodsId = pd.getString("periodsId");
			pd.put("periodsId", periodsId);	//期数ID
			shoppingcartService.delete(pd);
			result = new JsonResult(true,"购物车列表删除成功！");
		} catch (Exception e) {
			result = new JsonResult(false,"购物车列表删除异常！");
			logger.info("-----购物车列表删除出现异常------"+e);
		}
		return result;
		
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	@ResponseBody
	public Object edit() throws Exception{
		logBefore(logger, "修改ShoppingCart");
		JsonResult result = null;
		PageData pd = null;
		try{
			pd = this.getPageData();
			Logininfo current = UserContext.getCurrent();
			pd.put("userId", current.getId());	//用户ID
			shoppingcartService.edit(pd);
			result = new JsonResult(true,"购物车列表修改成功！");
		} catch (Exception e) {
			result = new JsonResult(false,"购物车列表修改异常！");
			logger.info("-----购物车列表修改出现异常------"+e);
		}
		return result;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	@ResponseBody
	public Object list(Page page){
		logBefore(logger, "列表ShoppingCart");
		JsonResult result = null;
		PageData pd = null;
		try{
			pd = this.getPageData();
			Logininfo current = UserContext.getCurrent();
			pd.put("userId", current.getId());	//用户ID
			page.setPd(pd);
			List<PageData>	varList = shoppingcartService.list(page);	//列出ShoppingCart列表
			result = new JsonResult(true,"购物车列表查询成功！",varList);
		} catch (Exception e) {
			result = new JsonResult(false,"购物车列表查询异常！");
			logger.info("-----购物车列表查询出现异常------"+e);
		}
		return result;
	}
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() {
		logBefore(logger, "批量删除ShoppingCart");
		JsonResult result = null;
		PageData pd = null;
		try {
			pd = this.getPageData();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				shoppingcartService.deleteAll(ArrayDATA_IDS);
			}
			result = new JsonResult(true,"购物车列表删除成功！");
		} catch (Exception e) {
			result = new JsonResult(false,"购物车列表删除异常！");
			logger.info("-----购物车列表删除出现异常------"+e);
		}
		return result;
	}
	
}