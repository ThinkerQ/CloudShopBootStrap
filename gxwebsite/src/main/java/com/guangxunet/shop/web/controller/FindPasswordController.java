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
import com.guangxunet.shop.base.system.PageData;
import com.guangxunet.shop.base.util.JsonResult;
import com.guangxunet.shop.base.util.LoggerUtil;
import com.guangxunet.shop.base.util.PhoneFormatCheckUtils;
import com.guangxunet.shop.business.service.IResetpwdVerifyService;
import com.guangxunet.shop.web.controller.base.BaseController;

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
	@Autowired
	private IResetpwdVerifyService resetpwdVerifyService;
	
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
			//验证手机号
			if (StringUtils.isEmpty(mobile)) {
				throw new RuntimeException("手机号为空！");
			}
			//手机号规则校验
			if (!PhoneFormatCheckUtils.isChinaPhoneLegal(mobile)) {
				throw new RuntimeException("手机号不符合规则，仅支持大陆手机号注册！");
			}
    		
			
			boolean exist = iLogininfoService.checkUserPhoneNumberExist(mobile);
			
			if (exist) {
				//若是注册用户才发送短信验证码
				logger.info("-----是注册用户才发送短信-------");
				verifyCodeService.sendVerifyCode(mobile);
			}
			
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
	public String findPwd3(String phoneNumber,String uuid,Model model) throws Exception{
		logger.info("------------findPwd3.screen------phoneNumber:"+phoneNumber);
		logger.info("------------findPwd3.screen------uuid:"+uuid);
		model.addAttribute("phoneNumber", phoneNumber);
		model.addAttribute("uuid", uuid);
		return "findPwd3";
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
			LoggerUtil.info("=======================验证结果===" + isVerifyCode);
			
			/**
			 *如果验证通过，往数据库插入一条记录，记录该用户修改密码的行为，返回一个UUID给用户.
			 *用户在下一步重置密码时需要带上这个正确的加密串才能重置密码。重置之后将之前的记录标记为已完成重置。 
			 */
			String uuid = resetpwdVerifyService.insertResetVerify(phoneNumber);//插入重置密码记录
			
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("phoneNumber", phoneNumber);
			resultMap.put("uuid", uuid);
			result = new JsonResult(isVerifyCode, "验证通过");
			result.setResult(resultMap);			
		} catch (Exception e) {
			result = new JsonResult(e.getMessage());
		}
		return result;
	}
	
	/**
     * 重置密码
     * @param phoneNumber
     * @param newPassword
     * @param uuid
     */
    @RequestMapping("/resetPassword.screen")
    @ResponseBody
    public JsonResult resetPassword(){
    	JsonResult result = null;
    	PageData pd = null;
    	pd = this.getPageData();
    	logger.info("---------------pd="+pd);
        try {
			
			//校验是够允许修改
			resetpwdVerifyService.validateByUUID(pd);
			
			/*
        	//手机号是否为已注册用户
        	boolean numberExist = iLogininfoService.checkUserPhoneNumberExist(phoneNumber);
        	
        	//修改用户密码
        	iLogininfoService.resetPassword(phoneNumber,newPassword);*/
        	
			result = new JsonResult(true,"密码重置成功！");
        } catch (Exception e) {
            result  = new JsonResult(e.getMessage());
            e.printStackTrace();
        }
    	
    	return result;
    }
}
