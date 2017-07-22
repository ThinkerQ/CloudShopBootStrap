package com.guangxunet.shop.web.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guangxunet.shop.base.service.ILogininfoService;
import com.guangxunet.shop.base.service.IVerifyCodeService;
import com.guangxunet.shop.base.util.JsonResult;
import com.guangxunet.shop.base.util.LoggerUtil;

/**发送验证码控制器
 * Created by King on 2016/10/11.
 */
@Controller
@RequestMapping("SendVerifyCode")
public class SendVerifyCodeController {
    @Autowired
    private IVerifyCodeService verifyCodeService;
    @Autowired
    private ILogininfoService logininfoService;

    /**
     * 发送短信验证码:用于注册
     * @param phoneNumber
     * @return
     */
    @RequestMapping("/sendVerifyCode.screen")
    @ResponseBody
    public JsonResult sendVerifyCode(String phoneNumber){
        JsonResult result = null;
        try {
	        	//手机号是否为已注册用户
	        	boolean numberExist = logininfoService.checkUserPhoneNumberExist(phoneNumber);
	        	if (numberExist) {
	        		throw new RuntimeException("手机号已被注册！");
	    		}
	        	
				verifyCodeService.sendVerifyCode(phoneNumber);
				result = new JsonResult(true,"发送成功");
        } catch (Exception e) {
            result  = new JsonResult(e.getMessage());
        }
        return result;
    }
    
    /**
     * 发送短信验证码:用于找回密码
     * @param phoneNumber
     * @return
     */
    @RequestMapping("/sendVerifyCodeForFindPwd.screen")
    @ResponseBody
    public JsonResult sendVerifyCodeForFindPwd(String phoneNumber){
    	JsonResult result = null;
        try {
	        	//手机号是否为已注册用户
	        	boolean numberExist = logininfoService.checkUserPhoneNumberExist(phoneNumber);
	        	if (!numberExist) {
	        		throw new RuntimeException("非注册用户！");
	    		}
	        	
				verifyCodeService.sendVerifyCode(phoneNumber);
				result = new JsonResult(true,"发送成功");
        } catch (Exception e) {
            result  = new JsonResult(e.getMessage());
        }
        return result;
    }
    
    /**
	 * 根据手机号和验证码验证验证码的正确性
	 * @param phone
	 * @param verifyCode
	 * @return
	 */
	@RequestMapping("/verifyCode.screen")
	@ResponseBody
	public JsonResult verifyCode(String phoneNumber,String verifyCode){
		JsonResult result =null;
		try {
			LoggerUtil.info("=======================入参===phone="+phoneNumber + ",code="+verifyCode);
			boolean isVerifyCode = verifyCodeService.verifyCode(phoneNumber, verifyCode);
			LoggerUtil.info("=======================验证结果===" + verifyCode);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("phoneNumber", phoneNumber);
			result = new JsonResult(isVerifyCode, "验证通过");
			result.setResult(resultMap);			
		} catch (Exception e) {
			result = new JsonResult(e.getMessage());
		}
		return result;
	}
}
