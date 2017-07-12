package com.guangxunet.shop.business.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.guangxunet.shop.base.domain.BaseDomain;
/**
 * 商品分类表实体类
 * @Description: 
 * @author King
 * @date 2017年7月3日
 */
public class Category extends BaseDomain{ 

    private String name;//分类名称

    private String parentId;//父级分类
    
//    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GTM+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createDate;//创建时间

//    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GTM+8")//后台显示到前端格式
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
}