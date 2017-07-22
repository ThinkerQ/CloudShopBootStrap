package com.guangxunet.shop.base.service.impl;


import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudTopic;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.BatchSmsAttributes;
import com.aliyun.mns.model.MessageAttributes;
import com.aliyun.mns.model.RawTopicMessage;
import com.aliyun.mns.model.TopicMessage;
import com.guangxunet.shop.base.service.ILogininfoService;
import com.guangxunet.shop.base.service.IVerifyCodeService;
import com.guangxunet.shop.base.util.BidConst;
import com.guangxunet.shop.base.util.DateUtils;
import com.guangxunet.shop.base.util.LoggerUtil;
import com.guangxunet.shop.base.util.PhoneFormatCheckUtils;
import com.guangxunet.shop.base.util.UserContext;
import com.guangxunet.shop.base.vo.VerifyCodeVO;

/**验证码服务实现类
 * Created by King on 2016/10/11.  
 */
@Service
public class VerifyCodeServiceImpl implements IVerifyCodeService {
	@Autowired
    private ILogininfoService logininfoService;
	
    @Value("${sms.url}")//简单值注入方式：属性文件中配置了url=http://localhost:8082/send.do
    private String url;

    @Value("${sms.username}")//sms.username=xmg
    private String username;

    @Value("${sms.password}")//sms.password=1111
    private String password;

    @Value("${sms.apikey}")//sms.apikey=1111
    private String apiKey;

    @Value("${alicloud.YourAccessId}")//账号
    private String YourAccessId;
    
    @Value("${alicloud.YourAccessKey}")//秘钥
    private String YourAccessKey;
    
    @Value("${alicloud.YourMNSEndpoint}")//url
    private String YourMNSEndpoint;
    
    @Value("${alicloud.YourTopic}")//主题
    private String YourTopic;
    
    @Value("${alicloud.YourSignName}")//签名
    private String YourSignName;
    
    @Value("${alicloud.YourSMSTemplateCode}")//短信模板
    private String YourSMSTemplateCode;
    
    /**
     * 验证手机验证码
     */
    @Override
    public boolean verifyCode(String phoneNumber, String verifyCode) throws Exception{
    	if (StringUtils.isEmpty(phoneNumber)) {
			throw new RuntimeException("手机号为空！");
		}
		
		if (StringUtils.isEmpty(verifyCode)) {
			throw new RuntimeException("验证码为空！");
		}
    	
        VerifyCodeVO vc =UserContext.getVerifyCode();
        LoggerUtil.info("===验证手机验证码==session中VO="+vc);
        
        if (vc == null) {
        	throw new RuntimeException("未曾向该手机号发送验证码或验证码已失效！");
		}
        
        if (!vc.getPhoneNumber().equals(phoneNumber)) {
        	throw new RuntimeException("手机号有误，请输入接收到验证码的手机号！");
		}
        
        if (!vc.getCode().equals(verifyCode)) {
        	throw new RuntimeException("验证码错误!");
		}
        
        if (DateUtils.getBetweenSecond(vc.getSendTime(),new Date()) > BidConst.SEND_VERIFY_EXPIRY_DATE) {
        	throw new RuntimeException("验证码已过期!");
		}
        
       boolean isSend =  vc!=null//发过短信
		                && vc.getPhoneNumber().equals(phoneNumber)//并且手机号和之前输入的一样
		                && vc.getCode().equals(verifyCode)//并且验证码相等
		                && DateUtils.getBetweenSecond(vc.getSendTime(),new Date()) <= BidConst.SEND_VERIFY_INTERVAL;//并且验证码没有过期，全部满足时返回true
        
      return isSend;
    }

    
    /**
     * 发送短信验证码：用于注册
     */
    public void sendVerifyCode(String phoneNumber) throws Exception{
    	//0.发送前校验：
    	this.validateBeforeSend(phoneNumber); 
        
        //1.生成一个验证码
        String code = UUID.randomUUID().toString().substring(0, 4);
        LoggerUtil.info("验证码是 "+code+"，打死也不能告诉别人哦！【阿里巴巴】");
        
        VerifyCodeVO vo = new VerifyCodeVO(code, phoneNumber, new Date());
        //把验证码vo对象放到session中
        UserContext.putVerifyCode(vo);
        
        // 发送短信
        //sendMessageByThisSystem(phoneNumber, code);//使用本系统模拟发送验证码
        this.batchPublishSMSMessage(phoneNumber,code);//通过阿里云短信服务发送验证码
            
    }

    
    /**
     * 通过本系统模拟发送短信验证码
     * @param phoneNumber
     * @param code
     */
	private void sendMessageByThisSystem(String phoneNumber, String code) {
		VerifyCodeVO vo;
		try {
		    // 创建一个URL对象
		    URL url = new URL(this.url);
		    // 通过URL得到一个HTTPURLConnection连接对象;
		    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		    // 拼接POST请求的内容
		    StringBuilder content = new StringBuilder(100)
		            .append("username=").append(username)
		            .append("&password=").append(password)
		            .append("&apikey=").append(apiKey).append("&mobile=")
		            .append(phoneNumber).append("&content=")
		            .append("验证码是:").append(code).append(",有效时间为")
		            .append(BidConst.SEND_VERIFY_INTERVAL).append("秒");
		    // 发送POST请求,POST或者GET一定要大写
		    conn.setRequestMethod("POST");
		    // 设置POST请求是有请求体的
		    conn.setDoOutput(true);
		    // 写入POST请求体
		    conn.getOutputStream().write(content.toString().getBytes());
		    // 得到响应流(其实就已经发送了)
		    String response = StreamUtils.copyToString(
		            conn.getInputStream(), Charset.forName("UTF-8"));
		    if (response.startsWith("success")) {
		        // 发送成功
		        // 把手机号码,验证码,发送时间装配到VO中并保存到session
		        vo = new VerifyCodeVO(code, phoneNumber, new Date());
		        //把vo放到session中
		        UserContext.putVerifyCode(vo);
		    } else {
		        // 发送失败
		        throw new RuntimeException();
		    }
		} catch (Exception e) {
		    throw new RuntimeException("短信发送失败!");
		}
	}

    /**
     * 短信验证码发送前校验
     */
    private void validateBeforeSend(String phoneNumber) {
    	//0.非空校验
    	if (StringUtils.isEmpty(phoneNumber)) {
    		throw new RuntimeException("手机号不能为空！");
		}
    	
    	//1.手机号合法性校验
    	boolean phoneLegal = PhoneFormatCheckUtils.isChinaPhoneLegal(phoneNumber);
    	if (!phoneLegal) {
    		throw new RuntimeException("手机号不正确！");
		}
    	
    	
    	
    	//3.发送时间间隔不可超出限制
        VerifyCodeVO vo = UserContext.getVerifyCode();
    	if (vo != null && DateUtils.getBetweenSecond(vo.getSendTime(), new Date()) <= BidConst.SEND_VERIFY_INTERVAL) {
    		throw new RuntimeException("发送过于频繁，每分钟只可获取一次验证码!");
    	}
    	
    	
    	//4.当天发送验证码次数是否超出限制(暂时不作校验)TODO
    	
	}


	/**
     * 通过阿里云短信发送接口发送验证码（可批量） 
	 * @throws UnsupportedEncodingException 
     */
	@Override
	public void batchPublishSMSMessage(String phoneNumber,String code) throws UnsupportedEncodingException {
			String _YourSignName = new String(YourSignName.getBytes("ISO-8859-1"),"utf-8");
			LoggerUtil.info("=======================签名===_YourSignName="+_YourSignName);
		/**
         * Step 1. 获取主题引用
         */
        CloudAccount account = new CloudAccount(YourAccessId, YourAccessKey, YourMNSEndpoint);
        MNSClient client = account.getMNSClient();
        CloudTopic topic = client.getTopicRef(YourTopic);
        /**
         * Step 2. 设置SMS消息体（必须）
         *
         * 注：目前暂时不支持消息内容为空，需要指定消息内容，不为空即可。
         */
        RawTopicMessage msg = new RawTopicMessage();
        msg.setMessageBody("sms-message");
        /**
         * Step 3. 生成SMS消息属性
         */
        MessageAttributes messageAttributes = new MessageAttributes();
        BatchSmsAttributes batchSmsAttributes = new BatchSmsAttributes();
        // 3.1 设置发送短信的签名（SMSSignName）
        batchSmsAttributes.setFreeSignName(_YourSignName);
        // 3.2 设置发送短信使用的模板（SMSTempateCode）
        batchSmsAttributes.setTemplateCode(YourSMSTemplateCode);
        // 3.3 设置发送短信所使用的模板中参数对应的值（在短信模板中定义的，没有可以不用设置）
        BatchSmsAttributes.SmsReceiverParams smsReceiverParams = new BatchSmsAttributes.SmsReceiverParams();
        smsReceiverParams.setParam("name", phoneNumber);//用户名
        smsReceiverParams.setParam("code", code);
        // 3.4 增加接收短信的号码
        batchSmsAttributes.addSmsReceiver(phoneNumber, smsReceiverParams);
        messageAttributes.setBatchSmsAttributes(batchSmsAttributes);
        try {
            /**
             * Step 4. 发布SMS消息
             */
            TopicMessage ret = topic.publishMessage(msg, messageAttributes);
            
            System.out.println("ret: " + ret);
            System.out.println("MessageId: " + ret.getMessageId());
            System.out.println("MessageMD5: " + ret.getMessageBodyMD5());
        } catch (ServiceException se) {
            System.out.println(se.getErrorCode() + se.getRequestId());
            System.out.println(se.getMessage());
            se.printStackTrace();
            throw new RuntimeException(se.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        client.close();
	}


	
}
