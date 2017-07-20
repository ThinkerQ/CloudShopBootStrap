package com.guangxunet.shop.business.service;

import java.util.List;
import java.util.Map;

import com.guangxunet.shop.base.system.PageData;
import com.guangxunet.shop.business.domain.Category;

/** 
* @author 作者 E-mail: King
* @version 创建时间：2017年7月4日 下午10:11:39 
* 类说明 
*/
public interface ICategoryService {
	/**
	 * 删除商品类目
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * 一级商品类目列表
	 */
	List<Category> selectCategoryOneListAll();

	/**
	 * 根据父级id查询二级类目
	 * @param id
	 * @return
	 */
	List<Map<String,Object>> selectByParentId(String id);
	
	/**
	 * 新增商品子级类目
	 * @param id
	 * @return
	 */
	int insertCateGorysItem(Map<String,Object> itemParams);

	/**
	 * 根据类目Id更新类目信息
	 * @param params
	 * @return
	 */
	int updateByPrimaryKey(Map<String, Object> params);
	
	/**
	 * 获取某一级类目
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getCategoryById(PageData pd) throws Exception;

	
}
