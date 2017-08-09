package com.guangxunet.shop.base.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.aspectj.apache.bcel.classfile.Constant;


/**
 * Created by chenmy on 2017/6/11.
 */
public class Constants {

    public static final String USER_IN_SESSION = "user_in_session";
    public static final String PERMISSION_IN_SESSION = "permission_in_session";
    public static final String MENU_IN_SESSION = "systemMenu_in_session";

    /** =========公共的图片路径=============== */
    public final static String PUBLIC_IMG_PATH = "E:/banner/imgPath";
    
    public static String COMMON_WEBSITE_ADDRESS = "";
    
    static {
        try {
            //加密的时候使用
            //InputStream is = Constant.class.getResourceAsStream(Constant.SystemConfigFile);
            //不加密的时候使用
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("sms.properties");
            Properties p = new Properties();
            p.load(is);
            COMMON_WEBSITE_ADDRESS = p.getProperty("common.website.address");
 
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
