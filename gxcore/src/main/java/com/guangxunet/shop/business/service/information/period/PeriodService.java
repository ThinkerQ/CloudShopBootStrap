package com.guangxunet.shop.business.service.information.period;

import com.guangxunet.shop.business.dao.DaoSupport;
import com.guangxunet.shop.base.system.Page;
import com.guangxunet.shop.base.system.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("periodService")
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
	
}

