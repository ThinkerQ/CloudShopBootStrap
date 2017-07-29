package com.guangxunet.shop.business.domain;

import java.util.Date;

import com.guangxunet.shop.base.domain.BaseDomain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter@Getter@ToString
public class ResetpwdVerify extends BaseDomain{

    private Long userId;

    private Date sendTime;

    private String uuid;
    
    private String effectFlag;
    

}