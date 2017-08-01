package com.guangxunet.shop.base.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**验证码VO对象
 * Created by King on 2016/10/11. 
 */
@Setter
@Getter
@ToString
public class VerifyCodeVO2 {
    private String code;//验证码
    private String phoneNumber;//手机号
    private Date sendTime;//发送时间
    private String content;//内容

    public String getContent(){
        return "您的验证码是：" + code + " , 有效时间为：60 秒。【中国强子国际】";
    }
    public VerifyCodeVO2() {
    }

    public VerifyCodeVO2(String code, String phoneNumber, Date sendTime) {
        this.code = code;
        this.phoneNumber = phoneNumber;
        this.sendTime = sendTime;
    }
}
