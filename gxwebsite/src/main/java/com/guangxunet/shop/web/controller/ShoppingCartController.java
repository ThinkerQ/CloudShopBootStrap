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
 * 创建人：Chenmy
 * 创建时间：2017-08-16
 */
@Controller
@RequestMapping(value="/shoppingCart")
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
			String userId = pd.getString("userId");
			if(StringUtils.hasLength(periodsId)&&StringUtils.hasLength(participantCount)){
				pd.put("userId",userId);	//用户ID
				pd.put("periodsId", periodsId);	//期数ID
				pd.put("participantCount", participantCount);	//参与人次
				pd.put("createTime", new Date());	//加入时间
				shoppingcartService.save(pd);
				result = new JsonResult(true,"购物车列表添加成功！");
			}else{
				new JsonResult(false,"期数或参与人次不能为空！");
			}
		} catch (Exception e) {
			result = new JsonResult(false,"购物车列表添加异常！");
			logger.error("-----购物车列表新增出现异常------"+e);
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
			
			String shoppingcart_ID = pd.getString("shoppingcart_ID");
			String participantCount = pd.getString("participantCount");//参与人次
			String userId = pd.getString("userId");
			if(StringUtils.hasLength(shoppingcart_ID)&&StringUtils.hasLength(participantCount)){
				pd.put("userId",userId);	//用户ID
				pd.put("shoppingcart_ID", shoppingcart_ID);	//购物车ID
				pd.put("participantCount", participantCount);	
				shoppingcartService.edit(pd);
				result = new JsonResult(true,"购物车列表修改成功！");
			}else{
				result = new JsonResult(false,"参数错误！");
			}
		} catch (Exception e) {
			result = new JsonResult(false,"购物车列表修改异常！");
			logger.error("-----购物车列表修改出现异常------"+e);
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
			String userId = pd.getString("userId");
			if(StringUtils.hasLength(userId)){
				pd.put("userId",userId);	//用户ID
				page.setPd(pd);
				List<PageData>	varList = shoppingcartService.list(page);	//列出ShoppingCart列表
				result = new JsonResult(true,"购物车列表查询成功！",varList);
			}else{
				result = new JsonResult(false,"参数错误！");
			}
		} catch (Exception e) {
			result = new JsonResult(false,"购物车列表查询异常！");
			logger.error("-----购物车列表查询出现异常------"+e);
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
			String DATA_IDS = pd.getString("shoppingcart_IDS");
			String userId = pd.getString("userId");
			if(StringUtils.hasLength(userId)&&StringUtils.hasLength(DATA_IDS)){
				pd.put("userId",userId);	//用户ID
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				shoppingcartService.deleteAll(ArrayDATA_IDS);
				result = new JsonResult(true,"购物车列表删除成功！");
			}else{
				result = new JsonResult(false,"参数错误！");
			}
		} catch (Exception e) {
			result = new JsonResult(false,"购物车列表删除异常！");
			logger.error("-----购物车列表删除出现异常------"+e);
		}
		return result;
	}
	
}
