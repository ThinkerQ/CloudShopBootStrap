package com.guangxunet.shop.business.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 商品实体表
 */
@Setter@Getter@ToString
public class Product {
    private Long id;//

    private Long categoryId;

    private String firstName;

    private String secondName;

    private String littleImgUrl;

    private String bigImgUrl;

    private Date createTime;

    private Integer status;


}