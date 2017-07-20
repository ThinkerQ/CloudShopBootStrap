package com.guangxunet.shop.business.mapper;

import java.util.List;
import java.util.Map;

import com.guangxunet.shop.base.system.PageData;
import com.guangxunet.shop.business.domain.Category;


public interface CategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Category record);

    Category selectByPrimaryKey(Long id);

    List<Category> selectAll();
    
    /**
     * 选择所有一级类目
     * @return
     */
    List<Category> selectOneAll();

    /**
	 * 根据类目Id更新类目信息
	 * @param params
	 * @return
	 */
    int updateByPrimaryKey(Map<String, Object> params);

    /**
	 * 根据父级id查询二级类目
	 * @param pid
	 * @return
	 */
	List<Map<String,Object>> selectByParentId(String pid); 

	/**
	 * 新增商品子级类目
	 * @param id
	 * @return
	 */
	int insertCateGorysItem(Map<String,Object> itemParams);
	
	/**
	 * 获取某一级类目
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getCategoryById(PageData pd) throws Exception;

}