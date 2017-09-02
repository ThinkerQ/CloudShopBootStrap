package com.guangxunet.shop.web.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.aliyun.mns.common.ServiceException;
import com.guangxunet.shop.base.system.PageData;
import com.guangxunet.shop.base.util.JsonResult;
import com.guangxunet.shop.business.service.IAddressService;
import com.guangxunet.shop.business.service.IProvincesService;
import com.guangxunet.shop.business.util.CollectionUtil;
import com.guangxunet.shop.web.controller.base.BaseController;

/** 
*@Title AddressContrller.java 
*@description 地址控制器
*@time 2017年9月2日 上午9:48:45 
*@author GENGSHUQIANG
*@version 1.0 
**/
@Controller
@RequestMapping("/Address")
public class AddressContrller extends BaseController{
	
	@Autowired
	private IProvincesService iProvincesService;
	@Autowired
	private IAddressService iAddressService;
	
	/**
	 * 跳转到地址页面
	 * @param phoneNumber
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/address.screen")
	public String address(String phoneNumber,Model model) throws Exception{
		logger.info("------------address.screen-----start------------------------");
		model.addAttribute("provinceList", iProvincesService.selectAllProvinces());
		return "address";
	}
	
	/**
	 * 获取全部省份
	 * @param id
	 * @return
	 */
	@RequestMapping("/getAllProvinces.screen")
    @ResponseBody
    public String getAllProvinces() {
    	List<PageData> allAllProvincesList = iProvincesService.selectAllProvinces();
    	logger.info("----------------------allAllProvincesList="+allAllProvincesList);
        return JSON.toJSONString(allAllProvincesList);
    }
	
	/**
	 * 根据省份id获取城市数据后直接使用@ResponseBody装成json数据
	 * @param id
	 */
	@RequestMapping("/getCityByProvinceId.screen")
    @ResponseBody
    public String getCityByProvinceId(@RequestParam(value = "id", required = false) Integer id) {
		logger.info("----------------------getAreaByCityId--省份id="+id);
		List<PageData> allCitisList = iProvincesService.selectAllCitisByPId(id);
		logger.info("----------------------allCitisList="+allCitisList);
        return JSON.toJSONString(allCitisList);
    }
	
	/**
     * 根据城市id获取区域数据后直接使用@ResponseBody装成json数据
     * 
     * @param id
     * @return
     */
    @RequestMapping("/getAreaByCityId.screen")
    @ResponseBody
    public String getAreaByCityId(@RequestParam(value = "id", required = false) Integer id) {
    	logger.info("----------------------getAreaByCityId--城市id="+id);
    	List<PageData> allAreasByCIdList = iProvincesService.selectAllAreasByCId(id);
    	logger.info("----------------------allAreasByCIdList="+allAreasByCIdList);
        return JSON.toJSONString(allAreasByCIdList);
    }
    
    /**
     * 根据用户id查询用户详细收货地址
     * @param id
     * @return
     */
    @RequestMapping("/getDetailAddressByUserId.screen")
    @ResponseBody
    public Object getDetailAddressByUserId(@RequestParam(value = "userId", required = false) String userId) {
    	JsonResult result = null;
    	try {
    		
    		if (StringUtils.isEmpty(userId)) {
				throw new RuntimeException("用户id不能为空！");
			}
    		
    		if (!StringUtils.isNumeric(userId)) {
    			throw new RuntimeException("用户id必须为数字！");
			}
    		
			logger.info("----------------------getDetailAddressByUserId--用户userId=" + userId);
			List<PageData> detailUserAddressList = iAddressService.selectDetailAddressByUserId(Integer.valueOf(userId));
			
			if (CollectionUtil.isEmpty(detailUserAddressList)) {
				throw new RuntimeException("您还暂无收货地址信息，快去设置吧！");
			}
			
			for (PageData pd : detailUserAddressList) {
				pd.put("detailAddress", pd.get("provinceName")+ pd.getString("cityName")+pd.get("areaName")+pd.get("addressDetail"));
			}
			
			logger.info("----------------------detailUserAddressList=" + detailUserAddressList);
			result = new JsonResult(true, "获取成功！", detailUserAddressList);
		} catch (Exception e) {
			result = new JsonResult(false,"查询异常："+e.getMessage());
			e.printStackTrace();
		}
		return result;
    }
    
}
