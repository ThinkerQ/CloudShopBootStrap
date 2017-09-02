package com.guangxunet.shop.business.mapper;

import java.util.List;

import com.guangxunet.shop.base.system.PageData;
import com.guangxunet.shop.business.domain.Provinces;

public interface ProvincesMapper {
    //int deleteByPrimaryKey(Integer id);

    //int insert(Provinces record);

	//int updateByPrimaryKey(Provinces record);
	
	/**
	 * 根据省份编号获取指定省份 
	 * @param id
	 * @return PageData
	 */
	PageData selectByPrimaryKey(Integer provinceId);

    /**
     * 获取全部省份 
     * @return
     */
    List<PageData> selectAll();
    
    /**
     * 获取省份的全部城市
     * @return
     */
    List<PageData> selectAllCitisByPId(Integer provinceId);
    
    /**
     * 获取城市的全部区域
     * @param cityId
     * @return
     */
    List<PageData> selectAllAreasByCId(Integer cityId);
    
}