package com.guangxunet.shop.base.mapper;


import com.guangxunet.shop.base.domain.Logininfo;
import com.guangxunet.shop.base.system.PageData;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LogininfoMapper {

    int insert(Logininfo record);

    List<Logininfo> selectByPrimaryKey(PageData pd);

    int updateByPrimaryKey(Logininfo record);

    int countUserByName(String username);
    
    int countUserByMobile(String mobile);

    Logininfo login(@Param("mobile") String mobile, @Param("password") String password,@Param("userType")int userType);

    Logininfo supervisorLogin(@Param("username") String username, @Param("password") String password,@Param("userType")int userType);

    int countByUserType(int userManager);

    List<Map<String,Object>> autoComplate(@Param("word")String word, @Param("userType")int userType);

    /**
     * 修改密码
     * @param phoneNumber
     * @param newPassword
     */
	int resetPassword(@Param("phoneNumber")String phoneNumber, @Param("newPassword")String newPassword);
	
	/**
	 * 更新用户头像
	 * @param pd
	 * @return
	 */
	int updateUserImgById(PageData pd);
}
