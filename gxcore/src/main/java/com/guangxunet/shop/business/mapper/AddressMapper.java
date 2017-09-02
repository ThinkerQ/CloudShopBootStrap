package com.guangxunet.shop.business.mapper;

import com.guangxunet.shop.base.system.PageData;
import com.guangxunet.shop.business.domain.Address;
import java.util.List;

public interface AddressMapper {
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