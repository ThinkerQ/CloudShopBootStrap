package com.guangxunet.shop.business.mapper;

import com.guangxunet.shop.business.domain.ResetpwdVerify;
import java.util.List;

public interface ResetpwdVerifyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ResetpwdVerify record);

    ResetpwdVerify selectByPrimaryKey(Integer id);
    
    ResetpwdVerify selectByUUID(String uuid);

    List<ResetpwdVerify> selectAll();

    int updateByPrimaryKey(ResetpwdVerify record);
}