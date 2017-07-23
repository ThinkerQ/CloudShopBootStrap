package com.test;

import java.util.Date;

import org.junit.Test;

import com.guangxunet.shop.base.system.DateUtil;
import com.guangxunet.shop.base.util.DateUtils;
import com.guangxunet.shop.base.util.LoggerUtil;

/** 
*@Title SpringTest.java 
*@description TODO 
*@time 2017年7月15日 下午10:21:34 
*@author GENGSHUQIANG
*@version 1.0 
**/
public class SpringTest {
	
	@Test
	public void testDate(){
		Date newDate = DateUtils.strToYYMMDDDate("2017-07-09");
        LoggerUtil.info("--newDate--"+newDate.toLocaleString());
        
        Date date = DateUtil.fomatDate("2017-07-09");
        LoggerUtil.info("--date--"+date);
        
	}
}
