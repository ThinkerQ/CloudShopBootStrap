package com.guangxunet.shop.business.service.information.shareorder;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guangxunet.shop.base.system.Page;
import com.guangxunet.shop.base.system.PageData;
import com.guangxunet.shop.business.dao.DaoSupport;


@Service("shareorderService")
public class ShareOrderService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("ShareOrderMapper.save", pd);
	}
	
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("ShareOrderMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("ShareOrderMapper.datalistPage", page);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("ShareOrderMapper.findById", pd);
	}
	
}

