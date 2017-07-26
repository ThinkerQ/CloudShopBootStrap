package com.fh.controller.information.product;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
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
import com.guangxunet.shop.business.service.information.category.CategoryService;
import com.guangxunet.shop.business.service.information.product.ProductService;
import com.guangxunet.shop.business.util.AppUtil;
import com.guangxunet.shop.business.util.PathUtil;

/** 
 * 类名称：ProductController
 * 创建人：FH 
 * 创建时间：2017-07-15
 */
@Controller
@RequestMapping(value="/product")
public class ProductController extends BaseController {
	
	String menuUrl = "product/list.do"; //菜单地址(权限用)
	@Resource(name="productService")
	private ProductService productService;
	@Resource(name="categoryService")
	private CategoryService categoryService;

	/**
	 * 上传
	 */
	@RequestMapping(value="/uploadImage")
	@ResponseBody
	public Object uploadImage(
			@RequestParam(required=false) MultipartFile file
	) throws Exception{
		logBefore(logger, "上传Product");
		Map<String,String> map = new HashMap<String,String>();
		String  ffile = DateUtil.getDays(), fileName = "";
		PageData pd = new PageData();
		if(Jurisdiction.buttonJurisdiction(menuUrl, "add")){
			if (null != file && !file.isEmpty()) {
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + ffile;		//文件上传路径
				fileName = FileUpload.fileUp(file, filePath, this.get32UUID());				//执行上传
			}else{
				System.out.println("上传失败");
			}
			//加水印
			Watermark.setWatemark(PathUtil.getClasspath() + Const.FILEPATHIMG + ffile + "/" + fileName);
		}
		map.put("imgPath", fileName);
		return ffile+ "/"+fileName;
	}

	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增Product");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("createTime", new Date());
		productService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Product");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			productService.delete(pd);
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
	public ModelAndView edit() throws Exception{
		logBefore(logger, "修改Product");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		productService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){

		logBefore(logger, "列表Product");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
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
			List<PageData>	varList = productService.list(page);	//列出Product列表
			mv.setViewName("information/product/product_list");
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
		logBefore(logger, "去新增Product页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			List<PageData> categoryList = categoryService.listAll(pd);
			mv.setViewName("information/product/product_edit");
			mv.addObject("msg", "save");
			mv.addObject("categoryList", categoryList);
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
		logBefore(logger, "去修改Product页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = null;
		pd = this.getPageData();
		try {
			pd = productService.findById(pd);	//根据ID读取
			List<PageData> categoryList = categoryService.listAll(pd);
			mv.setViewName("information/product/product_edit");
			mv.addObject("msg", "edit");
			mv.addObject("categoryList", categoryList);
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
		logBefore(logger, "批量删除Product");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				productService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出Product到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("分类");	//1
			titles.add("主名称");	//2
			titles.add("副名称");	//3
			titles.add("创建时间");	//4
			titles.add("状态");	//5
			titles.add("小图URL");	//6
			titles.add("大图URL");	//7
			dataMap.put("titles", titles);
			List<PageData> varOList = productService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i < varOList.size();i++){
				PageData vpd = new PageData();
							vpd.put("var1", varOList.get(i).get("categoryId").toString());	//1
							vpd.put("var2", varOList.get(i).getString("firstName"));	//2
							vpd.put("var3", varOList.get(i).getString("secondName"));	//3
							vpd.put("var4", varOList.get(i).getString("createTime"));	//4
							vpd.put("var5", varOList.get(i).get("status").toString());	//5
							vpd.put("var6", varOList.get(i).getString("littleImgUrl"));	//6
							vpd.put("var7", varOList.get(i).getString("bigImgUrl"));	//7
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
			PageData pd = new PageData();
			pd = this.getPageData();
			String PATH = pd.getString("PATH");													 		//图片路径
			DelAllFile.delFolder(PathUtil.getClasspath()+ Const.FILEPATHIMG + pd.getString("PATH")); 	//删除图片
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
}
