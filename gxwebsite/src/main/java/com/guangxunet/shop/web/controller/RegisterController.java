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
import com.guangxunet.shop.base.util.PhoneFormatCheckUtils;

import shaded.org.apache.commons.lang3.StringUtils;

/**注册相关服务
 * Created by Administrator on 2016/9/30.
 */
@Controller
@RequestMapping("Register")
public class RegisterController {
    @Autowired
    private ILogininfoService logininfoService;
    @Autowired
    private IVerifyCodeService iVerifyCodeService;
    /**
     * 前台新用户只能通过手机号注册
     * @param mobile
     * @param password
     * @return
     */
    @RequestMapping("/register.screen")
    @ResponseBody
    public JsonResult register(String mobile,String verifycode, String password,String confirmPwd){
        JsonResult result = null;
        try {
        	if (StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)||
        			StringUtils.isEmpty(confirmPwd)||
        			StringUtils.isEmpty(verifycode)
    			) {
				throw new RuntimeException("参数为空");
			}
        	
        	if (!StringUtils.equals(password, confirmPwd)) {
        		throw new RuntimeException("两次输入的密码不一致，请重新输入！");
			}
        	
        	//校验短信验证码是否正确
        	boolean isCodePoss = iVerifyCodeService.verifyCode(mobile,verifycode);
        	if (!isCodePoss) {
        		throw new RuntimeException("短信验证不通过！");
			}
        	
            logininfoService.register(mobile, password);
            result = new JsonResult(true,"注册成功！");
            Map<String,Object> resultmap  = new HashMap<String,Object>();
            resultmap.put("mobile", mobile);
            result.setResult(resultmap);
        } catch (Exception e) {
            result = new JsonResult(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 检查手机号是否被注册
     * @param mobile
     * @return Boolean
     */
    @RequestMapping("/checkUserByPhoneNumberIsExist.screen")
    @ResponseBody
    public Boolean checkUserPhoneNumberIsExist(String mobile){
    	if (StringUtils.isEmpty(mobile)) {
			return false;
		}
    	
    	Boolean isMobile = PhoneFormatCheckUtils.isChinaPhoneLegal(mobile);//手机号合法性校验
    	
    	return !logininfoService.checkUserPhoneNumberExist(mobile);
    }
    

}
