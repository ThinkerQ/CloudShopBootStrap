package com.guangxunet.shop.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guangxunet.shop.base.system.PageData;
import com.guangxunet.shop.business.mapper.ProvincesMapper;
import com.guangxunet.shop.business.service.IProvincesService;

/** 
*@Title ProvincesServiceImpl.java 
*@description TODO 
*@time 2017年9月2日 上午9:33:39 
*@author GENGSHUQIANG
*@version 1.0 
**/
@Service("iProvincesService")
public class ProvincesServiceImpl implements IProvincesService {
	@Autowired
	private ProvincesMapper provincesMapper;
	
	/**
	 * 根据省份编号获取指定省份 
	 * @param id
	 * @return PageData
	 */
	@Override
	public PageData selectByPrimaryKey(Integer provinceId) {
		return provincesMapper.selectByPrimaryKey(provinceId);
	}

	/**
     * 获取全部省份 
     * @return
     */
	@Override
	public List<PageData> selectAllProvinces() {
		return provincesMapper.selectAll();
	}

	/**
     * 获取省份的全部城市
     * @return
     */
	@Override
	public List<PageData> selectAllCitisByPId(Integer provinceId) {
		return provincesMapper.selectAllCitisByPId(provinceId);
	}

	 /**
     * 获取城市的全部区域
     * @param cityId
     * @return
     */
	@Override
	public List<PageData> selectAllAreasByCId(Integer cityId) {
		return provincesMapper.selectAllAreasByCId(cityId);
	}
	
}
