package com.guangxunet.shop.business.service;

import java.util.Map;

import com.guangxunet.shop.base.vo.VerifyCodeVO;
import com.guangxunet.shop.business.domain.VerifyCode;

/** 
*@Title ITVerifyCodeService.java 
*@description 验证码记录表对应的服务类
*@time 2017年7月31日 下午9:44:47 
*@author GENGSHUQIANG
**/
public interface ITVerifyCodeService {
	int insert(VerifyCode record);

    VerifyCode selectByPrimaryKey(Integer id);

    /*
     * 根据手机号码查询验证码
     */
    VerifyCode selectByPhoneNumber(Map<String, Object> params);
}
