package com.guangxunet.shop.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guangxunet.shop.base.service.IBannerService;
import com.guangxunet.shop.base.system.Page;
import com.guangxunet.shop.base.system.PageData;
import com.guangxunet.shop.base.util.Const;
import com.guangxunet.shop.base.util.JsonResult;
import com.guangxunet.shop.base.util.LoggerUtil;
import com.guangxunet.shop.business.service.information.advertisement.AdvertisementService;

/** 
* @author 作者 E-mail: King
* @version 创建时间：2017年6月11日 下午11:20:01 
* 类说明 广告Banner控制器
*/
@Controller
@RequestMapping("Banner")
public class BannerController {
	@Autowired
	private IBannerService iBannerService;
	@Autowired
	private AdvertisementService advertisementService;
	
	@Value("${gxshop.core.bannerLimitNumber}")
    private int bannerLimitNumber;
	
	@Value("${common.website.address}")
	private String WEBSITEPATH;//website域名访问地址前缀
	
	
	/**
	 * 获取banner列表
	 */
	@RequestMapping("/getBannerList.do")
	@ResponseBody()
	public JsonResult getBannerList(HttpServletRequest request,Page page){
		JsonResult result = null;
		PageData pd = null;
		try {
			LoggerUtil.info("==================获取banner列表=====page==="+page);
			List<PageData>	bannerList = advertisementService.list(page);
			
			//限制最多存放6条banner信息
			if (bannerList.size() > bannerLimitNumber) {
				bannerList = bannerList.subList(0, bannerLimitNumber);
			}
			
			for (PageData pageData : bannerList) {
				String image = WEBSITEPATH + pageData.getString("adsurl");
				pageData.put("adsurl", image);
			}
			
			//补充完成的图片路径
			LoggerUtil.info("==================获取banner列表=====结果===" + bannerList);
			
			if (bannerList != null && bannerList.size() > 0) {//集合非空
				result = new JsonResult(true, "获取成功！");
				result.setResult(bannerList);
			} else {
				result = new JsonResult("获取列表失败！");
			}
		} catch (Exception e) {
			result = new JsonResult(e.getMessage());
		}
		return result;
	}
	
	
	
	/**
	 * 获取banner列表（Banner表）
	 */
	/*@RequestMapping("/getBannerList.do")
	@ResponseBody()
	public JsonResult getBannerList(){
		JsonResult result = null;
		try {
			LoggerUtil.info("==================获取banner列表=====start===");
			List<Banner> bannerList = iBannerService.selectAll();
			
			//限制最多存放6条banner信息
			if (bannerList.size() > bannerLimitNumber) {
				bannerList = bannerList.subList(0, bannerLimitNumber);
			}
			
			//补充完成的图片路径
			for (Banner banner : bannerList) {
//				String image = BidConst.GET_IMAGES_PATH + banner.getImagePath();
				String image = banner.getImagePath();
				banner.setImagePath(image);
			}
			LoggerUtil.info("==================获取banner列表=====结果===" + bannerList);
			
			if (bannerList != null && bannerList.size() > 0) {//集合非空
				result = new JsonResult(true, "获取成功！");
				result.setResult(bannerList);
			} else {
				result = new JsonResult("获取列表失败！");
			}
		} catch (Exception e) {
			result = new JsonResult(e.getMessage());
		}
		return result;
	}*/
	
}
