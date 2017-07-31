package com.guangxunet.shop.business.domain;

import java.util.Date;

import com.guangxunet.shop.base.domain.BaseDomain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * 验证码
 * @author Administrator
 *
 */
@Setter@Getter
@ToString
@AllArgsConstructor
public class VerifyCode extends BaseDomain{

    private String phoneNumber;//接收手机号

    private Date sendTime;//发送时间 

    private String code;//验证码

    public VerifyCode(){
    	
    }
    
    public VerifyCode(String code, String phoneNumber, Date sendTime) {
        this.code = code;
        this.phoneNumber = phoneNumber;
        this.sendTime = sendTime;
    }

}