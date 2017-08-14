package com.guangxunet.shop.business.service.information.period;

import com.guangxunet.shop.business.dao.DaoSupport;
import com.guangxunet.shop.base.system.Page;
import com.guangxunet.shop.base.system.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("periodService")
@SuppressWarnings("unchecked")
public class PeriodService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("PeriodMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("PeriodMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("PeriodMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("PeriodMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("PeriodMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("PeriodMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("PeriodMapper.deleteAll", ArrayDATA_IDS);
	}

	/**
	 * 列表-按条件排序
	 */
	public List<PageData> listByOrder(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("PeriodMapper.listByOrder", pd);
	}
	
	/**
	 * 通过id查询期数详情
	 */
	public PageData queryPeriodById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("PeriodMapper.queryPeriodById", pd);
	}

	/**
	 * 列表-通过productID获取往期揭晓
	 */
	public List<PageData> queryPeriodsByProductId(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("PeriodMapper.queryPeriodsByProductId", pd);
	}

	/**
	 * 列表-通过商品分类Id获取商品期次列表
	 */
	public List<PageData> listByCategoryId(PageData pd) throws Exception{
		return (List<PageData>) dao.findForList("PeriodMapper.listByCategoryId", pd);
	}
	
	/**
	 * 获取获奖的用户和商品信息列表
	 * @return
	 * @throws Exception 
	 */
	public List<PageData> queryPrizedUserAndProduct() throws Exception {
		return (List<PageData>) dao.findForList("PeriodMapper.queryPrizedUserAndProduct",null);
	}
}

