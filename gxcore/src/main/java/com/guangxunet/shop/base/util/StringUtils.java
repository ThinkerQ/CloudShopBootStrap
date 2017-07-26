package com.guangxunet.shop.base.util;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2016/7/20.
 */
public class StringUtils {
    public static boolean hasLength(String string){
        return string!=null && !"".equals(string.trim());
    }
    
    /**
     * 获取用户手机号码的隐藏字符串
     * @param
     * @return
     */
    public static String getAnonymousPhoneNumber(String phoneNumber) {
        if (StringUtils.hasLength(phoneNumber)) {
            int len = phoneNumber.length();
            String replace = "";
            for (int i = 0; i < len; i++) {
                if ((i > 2 && i < 7)) {
                    replace += "*";
                } else {
                    replace += phoneNumber.charAt(i);
                }
            }
            return replace;
        }
        return phoneNumber;
    }
    
    /**
     * 字符重新编码
     * @param str
     * @return
     * @throws RuntimeException
     */
    public static String getReCharSet(String str) throws RuntimeException{
    	String newStr;
		try {
			newStr = new String(str.getBytes("ISO8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException("字符重新编码异常");
		}
    	return newStr;
    }
}

