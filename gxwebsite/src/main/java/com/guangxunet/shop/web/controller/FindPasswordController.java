package com.guangxunet.shop.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guangxunet.shop.base.service.ILogininfoService;
import com.guangxunet.shop.base.service.IVerifyCodeService;
import com.guangxunet.shop.base.util.JsonResult;
import com.guangxunet.shop.base.util.PhoneFormatCheckUtils;

import shaded.org.apache.commons.lang3.StringUtils;

/** 
*@Title FindPasswordController.java 
*@description 找回密码
*@time 2017年7月22日 下午1:50:27 
*@author GENGSHUQIANG
*@version 1.0 
**/
@Controller
@RequestMapping("FindPassword")
public class FindPasswordController extends BaseController{
	@Autowired
	private ILogininfoService iLogininfoService;
	@Autowired
    private IVerifyCodeService verifyCodeService;
	
	/**
     * 检查手机号是否已经被注册
     * @param username
     * @return
     */
    @RequestMapping("/checkUserByPhoneNumber.screen")
    @ResponseBody
    public JsonResult checkUserPhoneNumberExist(String mobile){
    	JsonResult result = null;
    	
    	try {
			/*//验证手机号
			if (StringUtils.isEmpty(mobile)) {
				throw new RuntimeException("手机号为空！");
			}
			//手机号规则校验
			if (!PhoneFormatCheckUtils.isChinaPhoneLegal(mobile)) {
				throw new RuntimeException("手机号不符合规则，仅支持大陆手机号注册！");
			}*/

			//发送短信验证码及发送前校验
			verifyCodeService.sendVerifyCode(mobile);
			
			boolean exist = iLogininfoService.checkUserPhoneNumberExist(mobile);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("isExist", exist);
			resultMap.put("mobile", mobile);
			
			result = new JsonResult(true,"调用成功！");
			result.setResult(resultMap);
		} catch (Exception e) {
			result = new JsonResult(false,e.getMessage());
		}  
		return result;
    }
	
	/**
	 * 忘记密码第二步：跳转到输入验证码页面
	 * @param phoneNumber
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/findPwd2.screen")
	public String findPwd2(String phoneNumber,Model model) throws Exception{
		logger.info("------------findPwd2.screen------phoneNumber:"+phoneNumber);
		model.addAttribute("phoneNumber", phoneNumber);
		return "findPwd2";
	}
	
	/**
	 * 忘记密码第三步：跳转到重置密码页面
	 * @param phoneNumber
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/findPwd3.screen")
	public String findPwd3(String phoneNumber,Model model) throws Exception{
		logger.info("------------findPwd3.screen------phoneNumber:"+phoneNumber);
		model.addAttribute("phoneNumber", phoneNumber);
		return "findPwd3";
	}
	
	/**
     * 重置密码
     * @param phoneNumber
     * @param newPassword
     */
    @RequestMapping("/resetPassword.screen")
    @ResponseBody
    public JsonResult resetPassword(String phoneNumber,String newPassword){
    	JsonResult result = null;
        try {
        	//验证手机号
			if (StringUtils.isEmpty(phoneNumber)) {
				throw new RuntimeException("手机号为空！");
			}
			
			if (StringUtils.isEmpty(newPassword)) {
				throw new RuntimeException("请输入新密码！");
			}
			
        	//手机号是否为已注册用户
        	boolean numberExist = iLogininfoService.checkUserPhoneNumberExist(phoneNumber);
        	if (!numberExist) {
        		throw new RuntimeException("非注册用户！");
    		}
        	
        	if (StringUtils.isEmpty(newPassword)) {
        		throw new RuntimeException("请填写新密码！");
			}
        	
        	//修改用户密码
        	iLogininfoService.resetPassword(phoneNumber,newPassword);
        	
			result = new JsonResult(true,"密码重置成功！");
        } catch (Exception e) {
            result  = new JsonResult(e.getMessage());
            e.printStackTrace();
        }
    	
    	return result;
    }
}
