package com.hnnd.stucommunity.common;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

@Component
public class EmailHelper {
	
	
	/**
	 * 创建验证邮件
	 * @param session
	 * @param recipients
	 * @param verifyCode
	 * @return
	 * @throws Exception
	 */
	public MimeMessage createVerifyMessage(Session session,String emailRecipients,String verifyCode) throws Exception{
		MimeMessage message=new MimeMessage(session);
		//发件人
		message.setFrom(new InternetAddress("18670473318@sina.cn"));
		//收件人
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailRecipients));
		//邮件标题
		message.setSubject("StuCommunity Support");
		//邮件内容
		message.setContent("<h4>欢迎注册StuCommunity,您的验证码是:</h4>"+"<a>"+verifyCode+"</a>","text/html;charset=gbk");
		
		return message;
	}
	
	/**
	 * 发送邮件
	 * @param recipients
	 * @param verifyCode
	 * @throws Exception
	 */
	public void sendEmail(String recipients,String verifyCode) throws Exception{
		
			Properties prop = new Properties();
		    prop.setProperty("mail.host", "smtp.sina.com");
		    prop.setProperty("mail.transport.protocol", "smtp");
		    prop.setProperty("mail.smtp.auth", "true");
	        //使用JavaMail发送邮件的5个步骤
		    //1、创建session
		    Session session = Session.getInstance(prop);
		    //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
		    //session.setDebug(true);
		    //2、通过session得到transport对象
		    Transport ts = session.getTransport();
		    //3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
	        ts.connect("smtp.sina.com", "18670473318@sina.cn", "iyaofly5221206");
		    //4、创建邮件
		    Message message = createVerifyMessage(session, recipients, verifyCode);
		    //5、发送邮件
		    ts.sendMessage(message, message.getAllRecipients());
		    ts.close();
		   
	}
	
	
	/*public static void main(String[] args) throws Exception {
		EmailHelper eh=new EmailHelper();
		eh.sendEmail("18670473318@163.com", "123456");
	}*/
	
	
	
}
