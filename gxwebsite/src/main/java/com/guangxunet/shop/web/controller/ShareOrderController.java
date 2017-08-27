package com.guangxunet.shop.web.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.guangxunet.shop.base.system.Page;
import com.guangxunet.shop.base.system.PageData;
import com.guangxunet.shop.base.util.StringUtils;
import com.guangxunet.shop.business.service.information.shareorder.ShareOrderService;
import com.guangxunet.shop.web.controller.base.BaseController;

/** 
 * 类名称：ShareOrderController
 * 创建人：FH 
 * 创建时间：2017-08-15
 */
@Controller
@RequestMapping(value="/shareOrder")
public class ShareOrderController extends BaseController {
	
	@Resource(name="shareOrderService")
	private ShareOrderService shareOrderService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增ShareOrder");
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		//pd.put("shareorder_ID", this.get32UUID());	//主键
		pd.put("userId", "");	//用户ID
		pd.put("periodsId", "");	//期数Id
		pd.put("isBlock", "");	//是否屏蔽
		pd.put("createTime", new Date());	//晒单时间
		shareOrderService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, "修改ShareOrder");
		ModelAndView mv = this.getModelAndView();
		PageData pd = null;
		pd = this.getPageData();
		shareOrderService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表ShareOrder");
		ModelAndView mv = this.getModelAndView();
		PageData pd = null;
		try{
			pd = this.getPageData();
			String keyword = pd.getString("keyword");
			if(StringUtils.hasLength(keyword)){
			pd.put("keyword",keyword);
			}
			page.setPd(pd);
			List<PageData>	varList = shareOrderService.list(page);	//列出ShareOrder列表
			mv.setViewName("information/shareorder/shareorder_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
}
