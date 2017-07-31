package com.guangxunet.shop.business.mapper;

import com.guangxunet.shop.base.vo.VerifyCodeVO;
import com.guangxunet.shop.business.domain.VerifyCode;
import java.util.List;
import java.util.Map;

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
}