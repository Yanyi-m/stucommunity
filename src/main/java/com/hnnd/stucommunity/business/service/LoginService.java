package com.hnnd.stucommunity.business.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnnd.stucommunity.business.cache.VerifyCodeCache;
import com.hnnd.stucommunity.business.dao.LoginDao;
import com.hnnd.stucommunity.business.model.User;
import com.hnnd.stucommunity.business.model.UserInformation;
import com.hnnd.stucommunity.common.EmailHelper;
import com.hnnd.stucommunity.common.GetIpUtil;
import com.hnnd.stucommunity.common.RandomCodeHelper;
import com.hnnd.stucommunity.common.base.ResultModel;
import com.hnnd.stucommunity.common.dictionary.DefineType;
import com.hnnd.stucommunity.common.encrypt.MD5;

@Service
public class LoginService {

	@Autowired
	LoginDao loginDao;
	@Autowired
	EmailHelper emailHelper;
	@Autowired
	VerifyCodeCache verifyCodeCache;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	public Map<String,Integer> timesMap=Collections.synchronizedMap(new HashMap());
	

	/**
	 * 检查用户名，密码是否正确
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ResultModel checkUserIsExist(HttpServletRequest request) throws Exception{
		String username=request.getParameter("username");
		String password=MD5.EncoderByMd5(request.getParameter("password"))+username;
		
		String password1=loginDao.findUser(username).getPassword();
		
		if(password1==null || password1.equals("")){
			return ResultModel.failModel("用户名不存在");
		} 
		if(password1.equals(password)){
			return ResultModel.successModel("密码正确");
		}
		
		return ResultModel.failModel("密码错误");
	}
	
	
	/**
	 * 检查该ip发送次数是否超过上限
	 * 上限暂时指定为20次
	 * @return
	 */
	public ResultModel checkTimes(HttpServletRequest request){
		String ip=GetIpUtil.getIp(request);
		
		if(timesMap.get(ip)==null){
			timesMap.put(ip, 1);
			return ResultModel.successModel();
		}else if(timesMap.get(ip)>20){
			return ResultModel.failModel();
		}
		
		timesMap.put(ip, timesMap.get(ip)+1);
		
		return ResultModel.successModel();
	}
	
	/**
	 * 发送邮件，Cache记录邮件和验证码
	 * @param emailRecipients
	 * @return
	 * @throws Exception
	 */
	public ResultModel sendVerifyEmail(HttpServletRequest request) throws Exception{
		String emailRecipients=request.getParameter("emailRecipients");
		
		//防止邮箱为空值
		if(emailRecipients.equals("")){
			return ResultModel.failModel();
		}
		
		//检查该ip发送次数是否超过上限
		if(checkTimes(request).isFail()){
			return ResultModel.failModel();
		}
		
		//获取验证码
		String verifyCode=RandomCodeHelper.randomNumCode(5);
		//发送邮件
		emailHelper.sendEmail(emailRecipients, verifyCode);
		//将验证码和邮箱存入cache便于验证
		verifyCodeCache.put(emailRecipients,verifyCode);
		
		logger.info("verifyCodeCache . email={},code={}",emailRecipients,verifyCode);
		
		return ResultModel.successModel();
	}
	
	/**
	 * 检查验证码是否正确
	 * @return
	 */
	public ResultModel checkVerifyCodeIsTure(String emailRecipients,String verifyCode){
		logger.info("check VerifyCode .email={},code={}",emailRecipients,verifyCode);
		
		if(verifyCodeCache.get(emailRecipients).equals(verifyCode)){
			return ResultModel.successModel();
		}
		
		return ResultModel.failModel();
	}
	
	/**
	 * 检查再次输入密码时是否一致
	 * @param firstpwd
	 * @param secondpwd
	 * @return
	 */
	public ResultModel checkReEnterPassword(String firstpwd,String secondpwd){
		if(firstpwd.equals(secondpwd)){
			return ResultModel.successModel();
		}
		
		return ResultModel.failModel();
	}
	
	
	/**
	 * 验证注册
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Integer checkRegister(HttpServletRequest request) throws Exception{
		String emailRecipients=request.getParameter("email");
		String verifyCode=request.getParameter("verifycode");
		String firstPwd=request.getParameter("password");
		String secondPwd=request.getParameter("confirm_password");
		String nickName=request.getParameter("nickname");
		
		if(checkVerifyCodeIsTure(emailRecipients, verifyCode).isFail()){
			return DefineType.FAIL_CODE;
		}
		//两次密码一致且密码长度大于6
		if(checkReEnterPassword(firstPwd, secondPwd).isFail() & firstPwd.length()>6){
			return DefineType.FAIL_CODE;
		}
		
		User user=new User();
		user.setUsername(emailRecipients);
		user.setPassword(firstPwd);
		user.setBlackUser(0);
		user.setAuthority(0);
		
		//插入用户
		user.setUserId(loginDao.saveUser(user));
		//插入用户信息
		Date jionDate=new Date();
		UserInformation userInfo=new UserInformation();
		userInfo.setJionDate(jionDate);
		userInfo.setUserId(user.getUserId());
		userInfo.setNickName(nickName);
		userInfo.setEmail(emailRecipients);
		
		loginDao.saveUserInformation(userInfo);
		
		
		logger.info("save the user,userid={},email={}",user.getUserId(),user.getUsername());

		return user.getUserId();
	}
	
	
	/**
	 * 24:00定时清理timesMap
	 */
	public void clearTimesMap(){
		timesMap.clear();
	} 
	
}
