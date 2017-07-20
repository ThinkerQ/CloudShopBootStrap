package com.guangxunet.shop.business.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.guangxunet.shop.base.domain.BaseDomain;
import com.guangxunet.shop.base.util.DateUtils;
import com.guangxunet.shop.base.util.LoggerUtil;
/**
 * 商品分类表实体类
 * @Description: 
 * @author King
 * @date 2017年7月3日
 */
public class Category extends BaseDomain{ 

    private String name;//分类名称

    private String parentId;//父级分类
    
    
//    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createDate;//创建时间

    
//    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updateDate;
    
    private String order;//排序

   

	public String getOrder() {
		return order;
	}



	public void setOrder(String order) {
		this.order = order;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getParentId() {
		return parentId;
	}



	public void setParentId(String parentId) {
		this.parentId = parentId;
	}



	public Date getCreateDate() {
		return createDate;
	}



	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}



	public Date getUpdateDate() {
		return updateDate;
	}



	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}



	@Override
	public String toString() {
		return "Category [name=" + name + ", parentId=" + parentId + ", createDate=" + createDate + ", updateDate="
				+ updateDate + "]";
	}
    
    
}