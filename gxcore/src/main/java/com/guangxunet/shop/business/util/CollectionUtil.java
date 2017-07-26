package com.guangxunet.shop.business.util;

import java.util.Collection;
import java.util.List;

/** 
*@Title CollectionUtil.java 
*@description 集合工具类
*@time 2017年7月24日 下午10:43:45 
*@author GENGSHUQIANG
*@version 1.0 
**/
public class CollectionUtil {
	
	/**
	 * 判断List集合是否为空
	 * @param list
	 * @return
	 */
	public static boolean isEmpty(List list){
		if (list == null) {
			return true;
		}
		
		if (list.size()<=0) {
			return true;
		}
		
		return false;
	}
}
