package com.fh.controller.information.advertisement;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.util.DelAllFile;
import com.fh.util.Jurisdiction;
import com.fh.util.ObjectExcelView;
import com.fh.util.Watermark;
import com.guangxunet.shop.base.system.DateUtil;
import com.guangxunet.shop.base.system.Page;
import com.guangxunet.shop.base.system.PageData;
import com.guangxunet.shop.base.util.Const;
import com.guangxunet.shop.base.util.FileUpload;
import com.guangxunet.shop.base.util.StringUtils;
import com.guangxunet.shop.business.service.information.advertisement.AdvertisementService;
import com.guangxunet.shop.business.util.AppUtil;
import com.guangxunet.shop.business.util.PathUtil;

/** 
 * 类名称：AdvertisementController
 * 创建人：FH 
 * 创建时间：2017-07-25
 */
@Controller
@RequestMapping(value="/advertisement")
public class AdvertisementController extends BaseController {
	
	String menuUrl = "advertisement/list.do"; //菜单地址(权限用)
	@Resource(name="advertisementService")
	private AdvertisementService advertisementService;
	
	@Value("${common.images.path}")
	private String commonImagesPath;//公共图片存放路径
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	@ResponseBody
	public Object save(@RequestParam(required=false) MultipartFile file) throws Exception{
		logger.info("-------------------save.do--新增Advertisement---begin");
		Map<String,String> map = new HashMap<String,String>();
		String  ffile = DateUtil.getDays(), fileName = "";
		PageData pd = new PageData();
		if(Jurisdiction.buttonJurisdiction(menuUrl, "add")){
			logger.info("-------------------save.do--inner1---begin");
			if (null != file && !file.isEmpty()) {
				logger.info("-------------------save.do--inner2---begin");
				String filePath = commonImagesPath + ffile;		//文件上传路径
				logger.info("-------------------filePath-="+filePath);
				logger.info("-------------------getClassResources-="+PathUtil.getClassResources());
				fileName = FileUpload.fileUp(file, filePath, this.get32UUID());				//执行上传
			}else{
				System.out.println("上传失败");
			}
			
			pd.put("adsurl", ffile + "/" + fileName);				//路径
			pd.put("createTime", new Date());	//创建时间
			pd.put("status", "1");	//状态
			//加水印 注释原因：广告图不需要添加水印 GENGSHUQIANG
//			Watermark.setWatemark(PathUtil.getClasspath() + Const.FILEPATHIMG + ffile + "/" + fileName);
//			Watermark.setWatemark(commonImagesPath + ffile + "/" + fileName);
			advertisementService.save(pd);
		}
		map.put("result", "ok");
		return AppUtil.returnObject(pd, map);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Advertisement");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = null;
		try{
			pd = this.getPageData();
//			DelAllFile.delFolder(PathUtil.getClasspath()+ Const.FILEPATHIMG + pd.getString("adsurl")); //删除图片
			DelAllFile.delFolder(commonImagesPath + pd.getString("adsurl")); //删除图片
			advertisementService.delete(pd);
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(
			HttpServletRequest request,
			@RequestParam(value="tp",required=false) MultipartFile file,
			@RequestParam(value="title",required=false) String title,
			@RequestParam(value="no",required=false) String no,
			@RequestParam(value="location",required=false) String location,
			@RequestParam(value="resolution",required=false) String resolution,
			@RequestParam(value="detailUrl",required=false) String detailUrl,
			@RequestParam(value="description",required=false) String description,
			@RequestParam(value="advertisement_ID",required=false) String advertisement_ID,
			@RequestParam(value="tpz",required=false) String tpz
			) throws Exception{
		logBefore(logger, "修改Advertisement");
		ModelAndView mv = this.getModelAndView();
		PageData pd = null;
		pd = this.getPageData();
		if(Jurisdiction.buttonJurisdiction(menuUrl, "edit")){//校验权限
			pd.put("title", title);	//
			pd.put("no", no);	//
			pd.put("location", location);	//
			pd.put("resolution", resolution);	//
			pd.put("detailUrl", detailUrl);	//
			pd.put("description", description);	//
			pd.put("advertisement_ID", advertisement_ID);	//
			
			logger.info("-----------save----pd="+pd);
			if(null == tpz){tpz = "";}
			String  ffile = DateUtil.getDays(), fileName = "";
			if (null != file && !file.isEmpty()) {
//				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + ffile;		//文件上传路径
				String filePath = commonImagesPath + ffile;		//文件上传路径
				fileName = FileUpload.fileUp(file, filePath, this.get32UUID());				//执行上传
				pd.put("adsurl", ffile + "/" + fileName);				//路径
			}else{
				pd.put("adsurl", tpz);
			}
//			Watermark.setWatemark(PathUtil.getClasspath() + Const.FILEPATHIMG + ffile + "/" + fileName);//加水印
//			Watermark.setWatemark(commonImagesPath + ffile + "/" + fileName);//加水印
			advertisementService.edit(pd);
		}
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表Advertisement");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = null;
		try{
			pd = this.getPageData();
			String keyword = pd.getString("keyword");
			if(StringUtils.hasLength(keyword)){
			pd.put("keyword",keyword);
			}
			String beginDate = pd.getString("beginDate");
			if(StringUtils.hasLength(beginDate)){
			pd.put("beginDate",beginDate);
			}
			String endDate = pd.getString("endDate");
			if(StringUtils.hasLength(endDate)){
			pd.put("endDate",endDate);
			}
			String status = pd.getString("status");
			if(StringUtils.hasLength(status)){
			pd.put("status",status);
			}
			page.setPd(pd);
			List<PageData>	varList = advertisementService.list(page);	//列出Advertisement列表
			mv.setViewName("information/advertisement/advertisement_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(){
		logBefore(logger, "去新增Advertisement页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = null;
		pd = this.getPageData();
		try {
			mv.setViewName("information/advertisement/advertisement_add");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit(){
		logBefore(logger, "去修改Advertisement页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = null;
		pd = this.getPageData();
		try {
			pd = advertisementService.findById(pd);	//根据ID读取
			mv.setViewName("information/advertisement/advertisement_edit");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() {
		logBefore(logger, "批量删除Advertisement");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = null;
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			List<PageData> pathList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				pathList = advertisementService.getAllById(ArrayDATA_IDS);
				//删除图片
				for(int i=0;i<pathList.size();i++){
//					DelAllFile.delFolder(PathUtil.getClasspath()+ Const.FILEPATHIMG + pathList.get(i).getString("adsurl"));
					DelAllFile.delFolder(commonImagesPath + pathList.get(i).getString("adsurl"));
				}
				advertisementService.deleteAll(ArrayDATA_IDS);
				pd.put("msg", "ok");
			}else{
				pd.put("msg", "no");
			}
			pdList.add(pd);
			map.put("list", pdList);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		return AppUtil.returnObject(pd, map);
	}
	
	/*
	 * 导出到excel
	 * @return
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel(){
		logBefore(logger, "导出Advertisement到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = null;
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("广告条编号");	//1
			titles.add("所在位置");	//2
			titles.add("分辨率");	//3
			titles.add("广告链接");	//6
			titles.add("图片路径");	//5
			titles.add("广告描述");	//7
			titles.add("创建时间");	//8
			titles.add("状态");	//9
			titles.add("标题");	//10
			dataMap.put("titles", titles);
			List<PageData> varOList = advertisementService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i < varOList.size();i++){
				PageData vpd = new PageData();
							vpd.put("var1", varOList.get(i).getString("no"));	//1
							vpd.put("var2", varOList.get(i).getString("location"));	//2
							vpd.put("var3", varOList.get(i).getString("resolution"));	//3
							vpd.put("var5", varOList.get(i).getString("detailUrl"));	//5
							vpd.put("var6", varOList.get(i).getString("adsurl"));	//6
							vpd.put("var7", varOList.get(i).getString("description"));	//7
							vpd.put("var8", varOList.get(i).getString("createTime"));	//8
							vpd.put("var9", varOList.get(i).get("status").toString());	//9
							vpd.put("var10", varOList.get(i).getString("title"));	//10
				varList.add(vpd);
			}
			dataMap.put("varList", varList);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv,dataMap);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	//删除图片
	@RequestMapping(value="/deltp")
	public void deltp(PrintWriter out) {
		logBefore(logger, "删除图片");
		try{
			PageData pd = this.getPageData();
			String PATH = pd.getString("adsurl");													 		//图片路径
//			DelAllFile.delFolder(PathUtil.getClasspath()+ Const.FILEPATHIMG + pd.getString("adsurl")); 	//删除图片
			DelAllFile.delFolder(commonImagesPath + pd.getString("adsurl")); 	//删除图片
			if(PATH != null){
				advertisementService.delTp(pd);																//删除数据中图片数据
			}	
			out.write("success");
			out.close();
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
	}
		
	/* ===============================权限================================== */
	public Map<String, String> getHC(){
		Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
		Session session = currentUser.getSession();
		return (Map<String, String>)session.getAttribute(Const.SESSION_QX);
	}
	/* ===============================权限================================== */
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
	
	public void test(){
		logger.info("-----------------test--------------");
	}
}
