package com.guangxunet.shop.base.service;

import java.util.Map;

/**
 * 发送邮件
 * @Description: 
 * @author King
 * @date 2017年7月12日
 */
public interface IEmailService {
    /**
     * 给指定邮箱发送验证邮件
     * @param email
     */
    void sendEmail(Map<String, Object> params) throws Exception;
}
