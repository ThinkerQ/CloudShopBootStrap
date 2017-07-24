package com.guangxunet.shop.business.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 期数表
 */
@Setter@Getter@ToString
public class T_Period {
    private Long id;

    private String periodNumber;//

    private Long prizeUserId;//获奖用户

    private Integer needCount;//需要人次

    private Integer alreadyCount;//已投人次

    private String prizeNumber;//幸运号码

    private Long productId;//商品ID

    private Date createTime;//创建时间

    private Date prizeTime;//获奖时间

    private Integer status;//状态

}