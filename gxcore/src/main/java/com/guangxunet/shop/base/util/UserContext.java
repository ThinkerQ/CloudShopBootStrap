package com.guangxunet.shop.base.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.guangxunet.shop.base.domain.Logininfo;
import com.guangxunet.shop.base.vo.VerifyCodeVO;

/** 
* @author 作者 E-mail: King
* @version 创建时间：2017年6月7日 下午9:25:48 
* 类说明 用户的上下文
*/
public class UserContext { 
	public static final String LOGININFO_IN_SESSION  = "logininfo";
	public static final String VERIFYCODE_IN_SESSION = "VERIFYCODE_IN_SESSION";
	
	public static HttpSession getSession(){
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
	}

	/**
	 * 由于安卓端每次访问没有自动携带sessionId导致session无法保持，所以后端返回一个sessionId给安卓端，每次请求都带上
	 * @return
	 */
	public static String getSessionId(){
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession().getId();
	}
	
	public static void putCurrent(Logininfo loginInfo) {
		getSession().setAttribute(LOGININFO_IN_SESSION,loginInfo);
	}
	
	public static Logininfo getCurrent(){
        return (Logininfo) getSession().getAttribute(LOGININFO_IN_SESSION);
    }
	
	public static VerifyCodeVO getVerifyCode() {
        return (VerifyCodeVO) getSession().getAttribute(VERIFYCODE_IN_SESSION);
    }
	
	public static void putVerifyCode(VerifyCodeVO codeVO){
        getSession().setAttribute(VERIFYCODE_IN_SESSION,codeVO);
    }
	
	public static HttpServletRequest get(){
        return local.get();
    }
	
	//定义一个方法，将线程中的request保存到线程中，有了request就可以获取session.
    public static ThreadLocal<HttpServletRequest> local = new ThreadLocal<>();
}
