package com.guangxunet.shop.business.domain;

import java.util.Date;

import com.guangxunet.shop.base.domain.BaseDomain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class T_Advertisement extends BaseDomain{
	
	private String no;//编号
	private String title;//标题
	private String location;//位置
	private String resolution;//分辨率
	private String fileSize;//建议文件大小
	private String fileFormat;//文件格式:
	private String adsurl;//链接
	private String description;//描述
	private Date createTime;//创建时间
	private Integer status;//状态
}
