package gxcore.test;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.guangxunet.shop.base.system.PageData;
import com.guangxunet.shop.base.util.LoggerUtil;
import com.guangxunet.shop.business.domain.Address;
import com.guangxunet.shop.business.service.IAddressService;
import com.guangxunet.shop.business.service.IProvincesService;

/** 
*@Title SpringTest.java 
*@description TODO 
*@time 2017年9月2日 上午9:29:34 
*@author GENGSHUQIANG
*@version 1.0 
**/
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringTest {
	@Autowired
	private IProvincesService iProvincesService;
	@Autowired
	private IAddressService iAddressService;
	
	/**
	 * 获取全国省市区
	 * @throws Exception
	 */
	@Test
	public void testSelectALlPCA() throws Exception {
		long start = System.nanoTime();
		//先获取全部的省份
    	List<PageData> allAllProvincesList = iProvincesService.selectAllProvinces();
    	LoggerUtil.info("----------------------allAllProvincesList="+allAllProvincesList);
    	//遍历省份，放入对应的城市
    	for (PageData province : allAllProvincesList) {
    		List<PageData> allCitisByPIdList = iProvincesService.selectAllCitisByPId(Integer.valueOf((String) province.get("provinceId")));
    		province.put("cities", allCitisByPIdList);
    		
    		for (PageData cityPd : allCitisByPIdList) {
    			List<PageData> allAreasByCIdList = iProvincesService.selectAllAreasByCId(Integer.valueOf((String) cityPd.get("cityId")));
    			cityPd.put("areas", allAreasByCIdList);
			}
    		
		}
    	long end = System.nanoTime();
    	LoggerUtil.info("----------------over------allAllProvincesList="+allAllProvincesList);
    	LoggerUtil.info("----------------over------用时="+(start-end));
    	
    	
    	//遍历省市，放入对应的区县
	}
	
	/**
	 * 添加收货地址
	 * @throws Exception
	 */
	@Test
	public void testInsertAddress() throws Exception {
		Address a = new Address();
		a.setAddressDetail("华夏东路益华路川北汽修厂对面陆家宅78号");
		a.setBizCode("455000");
		a.setContextName("耿术强");
		a.setPhoneNumber("18211674995");
		a.setProvinceId(140000);
		a.setCityId(140100);
		a.setAreaId(140105);
		a.setUserId(27);
		iAddressService.insert(a);
	}
	
	/**
	 * 更新收货地址
	 * @throws Exception
	 */
	@Test
	public void testUpdateAddress() throws Exception {
		Address a = new Address();
		a.setAddressDetail("华夏东路益华路川北汽修厂对面陆家宅78号");
		a.setBizCode("455000");
		a.setContextName("happy玲");
		a.setPhoneNumber("18211674995");
		a.setProvinceId(140000);
		a.setCityId(140100);
		a.setAreaId(140105);
		a.setUserId(27);
		a.setId(1);
		iAddressService.updateByPrimaryKey(a);
	}
	
	/**
	 * 选择某个用户的收货地址
	 * @throws Exception
	 */
	@Test
	public void testSelectById() throws Exception {
		Address primaryKey = iAddressService.selectByPrimaryKey(1);
		LoggerUtil.info("----------------primaryKey-"+primaryKey);
	}
	
	/**
	 * 根据用户id获取该用户的收货地址列表
	 * 省市县编号--省市县名称
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSelectAddressByUserId() throws Exception {
		
		List<PageData> pdList = iAddressService.selectDetailAddressByUserId(27);
		for (PageData pd : pdList) {
			pd.put("addressDetail", pd.get("provinceName")+ pd.getString("cityName")+pd.get("areaName")+pd.get("addressDetail"));
		}
		LoggerUtil.info("----------------pdList-"+pdList);

//		List<Address> userList = iAddressService.selectAddressByUserId(27);
//		LoggerUtil.info("----------------userList-"+userList);
		
		
	}
	
	/**
	 * 全国省市区联动测试
	 * @throws Exception
	 */
	@Test
	public void testProvince() throws Exception {
		List<PageData> allProvinces = iProvincesService.selectAllProvinces();
		LoggerUtil.info("==========allProvinces="+allProvinces);
	}
	
	/**
	 * 根据省份编号获取指定省份 
	 * @param id
	 * @return PageData
	 */
	@Test
	public void testSelectByPrimaryKey() throws Exception {
		PageData pd = iProvincesService.selectByPrimaryKey(140000);
		LoggerUtil.info("==========byPrimaryKey="+pd.get("province"));
	}
	
	/**
     * 获取省份的全部城市
     * @return
     */
	@Test
	public void testSelectAllCitisByPId() throws Exception {
		List<PageData> allCitisBList = iProvincesService.selectAllCitisByPId(140000);
		LoggerUtil.info("==========allCitisBList="+allCitisBList);
	}
	
	/**
     * 获取城市的全部区域
     * @param cityId
     * @return
     */
	@Test
	public void testSelectAllAreasByCId() throws Exception {
		List<PageData> allAreasByCId = iProvincesService.selectAllAreasByCId(140400);
		LoggerUtil.info("==========allAreasByCId="+allAreasByCId);
	}
	
}
