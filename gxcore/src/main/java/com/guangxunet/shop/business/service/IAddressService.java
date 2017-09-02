package com.guangxunet.shop.business.service;

import java.util.List;

import com.guangxunet.shop.base.system.PageData;
import com.guangxunet.shop.business.domain.Address;

/** 
*@Title IAddressService.java 
*@description 用户地址服务类
*@time 2017年9月2日 下午3:14:55 
*@author GENGSHUQIANG
*@version 1.0 
**/
public interface IAddressService {
	
	int deleteByPrimaryKey(Integer id);

    int insert(Address record);

    Address selectByPrimaryKey(Integer id);

    List<Address> selectAll();

    int updateByPrimaryKey(Address record);
    
    /**
     * 根据用户id获取该用户的收货地址列表
     * @param id
     */
    List<Address> selectAddressByUserId(Integer id);
    
    /**
     * 根据用户id查询用户详细收货地址
     * @param id
     * @return
     */
    List<PageData> selectDetailAddressByUserId(Integer id);
}
