package com.guangxunet.shop.base.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.guangxunet.shop.base.service.IEmailService;
import com.guangxunet.shop.base.util.LoggerUtil;
import com.guangxunet.shop.base.util.MapParamsUtil;

/**
 * 发送邮件
 * @Description: 
 * @author King
 * @date 2017年7月12日
 */
@Service
public class SendEmailServiceImpl implements IEmailService {

	/*
	 *  发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
     *  PS: 某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（有的邮箱称为“授权码”）, 
     *  对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
     */
	 @Value("${sms.myEmailAccount}")
     private String myEmailAccount;
	 
	 @Value("${sms.myEmailPassword}")
	 private String myEmailPassword;

    
    /* 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
     *	网易163邮箱的 SMTP 服务器地址为: smtp.163.com
     */
	 @Value("${sms.myEmailSMTPHost}")
	 private String myEmailSMTPHost;
	
	 private static String[] paramsArr = {"EMAIL","TYPE","TITLE","CONTENT"};//邮件所需要的参数
	 
	 
    @Override
    public void sendEmail(Map<String, Object> params) throws Exception {
    	//参数非空检验
    	MapParamsUtil.vlidateParamsIsNotEmpty(paramsArr, params);
    	
    	//1.创建参数配置，用于连接邮件服务器的参数配置
		Properties props = new Properties();//用于连接邮件服务器的参数配置
		props.setProperty("mail.transport.protocol", "smtp");//使用的协议
		props.setProperty("mail.smtp.host", myEmailSMTPHost);//发件人邮箱服务器地址
		props.setProperty("mail.smtp.auth", "true");//需要请求认证

		//某些邮件服务器要求SMTP连接是使用SSL认证，下面开启SSL安全连接
		/*final String smtpPort = "465";
		props.setProperty("mail.smtp.port", smtpPort);
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.socketFactory.port", smtpPort);*/
		
		//2.根据参数配置创建会话对象
		Session session = Session.getDefaultInstance(props);
		session.setDebug(true);//设置为debug模式, 可以查看详细的发送 log
		
		//3.创建邮件对象
		MimeMessage message = creatMimeMessage(session,myEmailAccount,params);
		
		// 4. 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();
		
        // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
        // 
        //    PS_01: 成败的判断关键在此一句, 如果连接服务器失败, 都会在控制台输出相应失败原因的 log,
        //           仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接, 根据给出的错误
        //           类型到对应邮件服务器的帮助网站上查看具体失败原因。
        //
        //    PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
        //           (1) 邮箱没有开启 SMTP 服务;
        //           (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
        //           (3) 邮箱服务器要求必须要使用 SSL 安全连接;
        //           (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
        //           (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
        //
        //    PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。
        transport.connect(myEmailAccount, myEmailPassword);
        
        //6.发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        
        //7.关闭连接
        transport.close();
        
        LoggerUtil.info("====邮件发送成功success=====");
    }

    /**
     * 批量发送邮件
     * @param session
     * @param sendEmail 发件箱
     * @param params 邮件信息
     * @return
     * @throws Exception
     */
    private static MimeMessage creatMimeMessage(Session session, String sendEmail, Map<String, Object> params) throws Exception {
    	String email = (String) params.get("EMAIL");//收件人邮箱
    	String TITLE = (String) params.get("TITLE");//邮件主题
    	String CONTENT = (String) params.get("CONTENT");//邮件内容
    	String TYPE = (String) params.get("TYPE");//类型
    	String isAll = (String) params.get("isAll");//是否群发
    	
    	
		//1. 创建一封邮件
		MimeMessage message = new MimeMessage(session);
		
		//2.From 发件人
		Address addresses = new InternetAddress(sendEmail,TITLE,"UTF-8");
		message.setFrom(addresses);
		
		//3.To 收件人
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email,TITLE,"UTF-8"));//单个收件人
		
		//设置多个收件人
		/*Address[] addressesArr= new Address[receiveMail.length];
		for (int i = 0; i < receiveMail.length; i++) {
			LoggerUtil.info("==========收件箱：======"+receiveMail[i]);
			Address adds = new InternetAddress(receiveMail[i],"亲爱的小宝","UTF-8");
			addressesArr[i] = adds;
		}
		
		message.setRecipients(MimeMessage.RecipientType.TO, addressesArr);*/
		
		//4.邮件主题
		message.setSubject(TITLE);
		
		//5.邮件正文（可以使用HTML标签）
		message.setContent(CONTENT, "text/html;charset=UTF-8");
		
		//6.设置显示的发件时间
		message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
	}
}
