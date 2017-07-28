package com.guangxunet.shop.base.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter@Getter
@ToString
public class Logininfo {
    public static final int USER_NORMAL = 0;//前台用户
    public static final int USER_MANAGER = 1;//后台用户


    //用户的正常登陆状态
    public static int STATE_NOMAL = 0;
    private Long id;
    private int state;
    private String username;
    private String password;
    private String mobile;
    private String email;
    private String userImgUrl;
    private int userType = USER_NORMAL;//用户类型：区分是前台用户还是后台用户,默认是普通用户
}