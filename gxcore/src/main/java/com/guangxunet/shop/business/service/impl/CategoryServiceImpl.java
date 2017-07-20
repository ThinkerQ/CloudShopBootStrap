package com.guangxunet.shop.business.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guangxunet.shop.base.system.PageData;
import com.guangxunet.shop.business.domain.Category;
import com.guangxunet.shop.business.mapper.CategoryMapper;
import com.guangxunet.shop.business.service.ICategoryService;

/** 
* @author 作者 E-mail: King
* @version 创建时间：2017年7月4日 下午10:12:28 
* 类说明 
*/
@Service("iCategoryService")
public class CategoryServiceImpl implements ICategoryService {
	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public List<Category> selectCategoryOneListAll() {
		return categoryMapper.selectOneAll();
	}

	@Override
	public List<Map<String,Object>> selectByParentId(String id) {
		return categoryMapper.selectByParentId(id);
	}

	@Override
	public int insertCateGorysItem(Map<String, Object> itemParams) {
		itemParams.put("createDate", new Date());
		itemParams.put("updateDate", new Date());
		return categoryMapper.insertCateGorysItem(itemParams);
	}

	@Override
	public int updateByPrimaryKey(Map<String, Object> params) {
		params.put("updateDate", new Date());
		return categoryMapper.updateByPrimaryKey(params);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return categoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public PageData getCategoryById(PageData pd) throws Exception {
		return categoryMapper.getCategoryById(pd);
	}

	
}
