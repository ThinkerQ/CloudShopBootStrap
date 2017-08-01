package com.guangxunet.shop.business.mapper;

import java.util.List;
import java.util.Map;

import com.guangxunet.shop.business.domain.VerifyCode;

public interface VerifyCodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VerifyCode record);

    VerifyCode selectByPrimaryKey(Integer id);

    List<VerifyCode> selectAll();

    int updateByPrimaryKey(VerifyCode record);

    /**
     * 根据手机号码查询验证码
     * @param params
     * @return
     */
    VerifyCode selectByPhoneNumber(Map<String, Object> params);
    
    /**
     * 根据手机号获取最近发送的验证码
     * @param phoneNumber
     * @return
     */
	VerifyCode selectCurrentCodeByPhoneNumber(String phoneNumber);
}