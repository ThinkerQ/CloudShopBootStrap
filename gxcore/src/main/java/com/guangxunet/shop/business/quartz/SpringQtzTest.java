package com.guangxunet.shop.business.quartz;

import java.util.Date;

import com.guangxunet.shop.base.util.LoggerUtil;

/** 
*@Title SpringQtzTest.java 
*@description TODO 
*@time 2017年8月25日 下午10:38:46 
*@author GENGSHUQIANG
*@version 1.0 
**/
public class SpringQtzTest {
	private static int counter = 0;  
    protected void execute()  {  
    	LoggerUtil.info("=====================Quartz==SpringQtzTest开始执行=====每5秒执行一次==");
        long ms = System.currentTimeMillis();  
        System.out.println("\t\t" + new Date(ms));  
        System.out.println("(" + counter++ + ")");  
        LoggerUtil.info("=====================Quartz==SpringQtzTest执行结束=====每5秒执行一次==");
    }  
}
