package com.guangxunet.shop.base.util;

import java.util.UUID;

public class UuidUtil {

	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}
	
	/**
     * 随机生成6位随机验证码
      * 方法说明
      * @Discription:扩展说明
      * @return
      * @return String
      * @Author: feizi
      * @Date: 2015年4月17日 下午7:19:02
      * @ModifyUser：feizi
      * @ModifyDate: 2015年4月17日 下午7:19:02
     */
    public static String createRandomVcode(){
        //验证码
        String vcode = "";
        for (int i = 0; i < 6; i++) {
            vcode = vcode + (int)(Math.random() * 9);
        }
        return vcode;
    }
	
	public static void main(String[] args) {
		System.out.println(get32UUID());
	}
}

