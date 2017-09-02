package com.guangxunet.shop.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guangxunet.shop.base.system.PageData;
import com.guangxunet.shop.business.domain.Address;
import com.guangxunet.shop.business.mapper.AddressMapper;
import com.guangxunet.shop.business.service.IAddressService;

/** 
*@Title AddressServiceImpl.java 
*@description 用户地址服务类
*@time 2017年9月2日 下午3:15:24 
*@author GENGSHUQIANG
*@version 1.0 
**/
@Service("iAddressService")
public class AddressServiceImpl implements IAddressService {
	@Autowired
	private AddressMapper addressMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return addressMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Address record) {
		return addressMapper.insert(record);
	}

	@Override
	public Address selectByPrimaryKey(Integer id) {
		return addressMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Address> selectAll() {
		return addressMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(Address record) {
		return addressMapper.updateByPrimaryKey(record);
	}

	/**
     * 根据用户id获取该用户的收货地址列表
     * @param id
     */
	@Override
	public List<Address> selectAddressByUserId(Integer id) {
		return addressMapper.selectAddressByUserId(id);
	}

	/**
     * 根据用户id查询用户详细收货地址
     * @param id
     * @return
     */
	@Override
	public List<PageData> selectDetailAddressByUserId(Integer id) {
		return addressMapper.selectDetailAddressByUserId(id);
	}
	
	
}
