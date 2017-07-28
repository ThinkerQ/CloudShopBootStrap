package com.guangxunet.shop.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.guangxunet.shop.base.domain.Logininfo;
import com.guangxunet.shop.base.service.ILogininfoService;
import com.guangxunet.shop.base.service.IVerifyCodeService;
import com.guangxunet.shop.base.system.DateUtil;
import com.guangxunet.shop.base.system.PageData;
import com.guangxunet.shop.base.util.Const;
import com.guangxunet.shop.base.util.FileUpload;
import com.guangxunet.shop.base.util.JsonResult;
import com.guangxunet.shop.base.util.UserContext;
import com.guangxunet.shop.business.util.AppUtil;
import com.guangxunet.shop.business.util.CollectionUtil;
import com.guangxunet.shop.business.util.PathUtil;

/** 
* @author 作者 E-mail: King
* @version 创建时间：2017年6月7日 下午9:14:53 
* 类说明 前台系统用户登录
*/
@Controller
@RequestMapping("Login")
public class LoginInfoController extends BaseController{
	@Autowired
	private ILogininfoService iLogininfoService;
	@Autowired
    private IVerifyCodeService verifyCodeService;
	/**
	 * 客户端用户登录
	 * @param username
	 * @param password
	 * @param request
	 * @return 登录成功则返回登录者信息
	 */
	@RequestMapping("/login.do")
	@ResponseBody
	public JsonResult login(String mobile, String password, HttpServletRequest request){
		JsonResult result = null;
		
		try {
			//非空检验
			if (StringUtils.isEmpty(mobile)) {
				throw new RuntimeException("手机号为空！");
			}
			if (StringUtils.isEmpty(password)) {
				throw new RuntimeException("密码为空！");
			}
			//登录校验
			Logininfo login = iLogininfoService.login(mobile, password, request.getRemoteAddr(), Logininfo.USER_NORMAL);
			logger.info("----登陆用户信息---"+login);
			if (login == null) {
				result = new JsonResult("手机号或密码错误，请重试！");
			} else {
				/*Logininfo current = UserContext.getCurrent();
				Map<String, Object> userInfo = new HashMap<String, Object>();
				userInfo.put("UId", current.getId());
				userInfo.put("userName", current.getUsername());
				userInfo.put("mobile", current.getMobile());
				userInfo.put("userType", current.getUserType());*/
				result = new JsonResult(true, "登录成功！");
				result.setResult(login);
			} 
		} catch (Exception e) {
			result = new JsonResult(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 去修改用户信息页面
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit(){
		logBefore(logger, "去修改用户信息页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			Logininfo current = UserContext.getCurrent();
			logger.info("----------------goEdit.do----------current="+current);
//			pd = iLogininfoService.selectByPrimaryKey(pd);	//根据ID读取
			mv.setViewName("system/user/edit");
			mv.addObject("current", current);
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 获取登录用户信息接口
	 * @return
	 */
	@RequestMapping("getLoginInfoById")
	@ResponseBody
	public JsonResult getLoginInfoById(){
		logBefore(logger,"获取登录用户信息接口");
		JsonResult result = null;
		PageData pd = new PageData();
		pd = this.getPageData();
		
		String id = pd.getString("id");
		
		try {
			if (StringUtils.isEmpty(id)) {
				throw new RuntimeException("用户id不能为空！");
			}
			List<Logininfo> loginList = iLogininfoService.selectByPrimaryKey(pd); //根据ID读取
			logger.info("-------------loginList=" + loginList);
			
			if (CollectionUtil.isEmpty(loginList)) {
				result = new JsonResult(false, "获取失败,未查询到该id对应的用户信息!");
			}else{
				Logininfo login = loginList.get(0);
				result = new JsonResult(true, "获取成功！", login);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result = new JsonResult(false, "查询异常："+e.getMessage());
		}
		return result;
	}
	
	/**
	 * 去首页
	 */
	@RequestMapping(value="/personal")
	public ModelAndView personal(){
		logBefore(logger, "登录成功进入首页");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			Logininfo currentLoginInfo = UserContext.getCurrent();
			logger.info("--------------当前登陆者currentLoginInfo："+currentLoginInfo);
			
//			pd = iLogininfoService.selectByPrimaryKey(pd);	//根据ID读取
			mv.setViewName("index");
			mv.addObject("currentLoginInfo", currentLoginInfo);
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 修改用户头像
	 * @return
	 */
	@RequestMapping(value="/editUserImage")
	@ResponseBody
	public Object editUserImage(@RequestParam(required=false) MultipartFile file){
		PageData pd = this.getPageData();;
		logger.info("-------------editUserImage.do-----pd="+pd);
		String  ffile = DateUtil.getDays(), fileName = "";
		if (null != file && !file.isEmpty()) {
			String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + ffile;		//文件上传路径
			fileName = FileUpload.fileUp(file, filePath, this.get32UUID());				//执行上传
			logger.info("-------------filePath-----"+filePath);
			logger.info("-------------fileName-----"+fileName);
			
			pd.put("userImgUrl", ffile+ "/"+fileName);
			//更新用户表头像文件名
			iLogininfoService.updateUserImgById(pd);
			
		}else{
			logger.info("-------------editUserImage.do-----上传失败");
		}
		
		return ffile+ "/"+fileName;
	} 
	
}
