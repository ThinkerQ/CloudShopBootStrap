package com.guangxunet.shop.gxmgrsite.web.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guangxunet.shop.base.service.IEmailService;
import com.guangxunet.shop.base.util.JsonResult;
import com.guangxunet.shop.base.util.LoggerUtil;

/**
 * 发送邮箱控制器 cm 
 * @Description: 
 * @author King
 * @date 2017年7月12日
 */
@Controller
public class SendEmailController {
    @Autowired
    private IEmailService emailService;

   /* @RequestMapping("sendEmail.do")
    @ResponseBody
    public JsonResult sendEmail(@RequestParam Map<String, Object> params){
    	LoggerUtil.info("---------------入参params---------"+params);
    	 JsonResult result = null;
        try {
            emailService.sendEmail(params);
            List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
            Map<String,Object> resultMap = new HashMap<String,Object>();
            resultMap.put("msg", "ok");
            list.add(resultMap);
            result.setResult(list);
        } catch (Exception e) {
            result  = new JsonResult(e.getMessage());
        }
        return result;
    }*/

}
