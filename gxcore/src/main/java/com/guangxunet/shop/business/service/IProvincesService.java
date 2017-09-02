package com.guangxunet.shop.business.service;

import java.util.List;

import com.guangxunet.shop.base.system.PageData;

/** 
*@Title IProvincesService.java 
*@description TODO 
*@time 2017年9月2日 上午9:32:19 
*@author GENGSHUQIANG
*@version 1.0 
**/
public interface IProvincesService {
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
    List<PageData> selectAllProvinces();
    
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
