package com.guangxunet.shop.business.service.information.category;

import com.guangxunet.shop.business.dao.DaoSupport;
import com.guangxunet.shop.base.system.Page;
import com.guangxunet.shop.base.system.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("categoryService")
public class CategoryService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("CategoryMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("CategoryMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("CategoryMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("CategoryMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("CategoryMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("CategoryMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("CategoryMapper.deleteAll", ArrayDATA_IDS);
	}

	/*
	* 上一级分类
	*/
	public List<PageData> parentList() throws Exception {
		return (List<PageData>)dao.findForList("CategoryMapper.parentList",null);
	}

	public List<Map<String, Object>> selectByParentId(String parentId) throws Exception {
		return (List<Map<String, Object>>) dao.findForList("CategoryMapper.selectByParentId", parentId);
	}
}

