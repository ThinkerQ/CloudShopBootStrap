package com.guangxunet.shop.base.util;

import java.util.Collection;
import java.util.Map;

import shaded.org.apache.commons.lang3.StringUtils;

/** 
* @author 作者 E-mail: King
* @version 创建时间：2017年7月12日 下午4:31:25 
* 类说明 Map参数非空检验工具类
*/
public class MapParamsUtil {
	
	public static void vlidateParamsIsNotEmpty(String[] arr,Map<String,Object> targetMap) throws Exception{
		if (arr.length<=0) {
			throw new Exception("参数数组不能为空");
		}
		
		if (targetMap==null) {
			throw new Exception("Map不能为空");
		}
		
		for (String parmStr : arr) {
			Object object = targetMap.get(parmStr);
			
			if (object==null) {
				throw new Exception(parmStr+"不能为空");
			}
			
			if (object instanceof String) {
				String str_obj = (String) object;
				if (StringUtils.isEmpty(str_obj)) {
					throw new Exception(parmStr+"不能为空");
				}
			}
			
		}
	}
}
