package com.hnnd.stucommunity.business.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnnd.stucommunity.business.service.LoginService;
import com.hnnd.stucommunity.common.base.ResultModel;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;
	
	@RequestMapping("/sendVerifyEmail")
	@ResponseBody
	public ResultModel sendVerifyEmail(HttpServletRequest request) throws Exception{
		
		ResultModel resultModel=loginService.sendVerifyEmail(request);
		if(resultModel.isFail()){
			return ResultModel.failModel();
		}
		
		return ResultModel.successModel();
	}
	
	@RequestMapping("/checkRegister")
	@ResponseBody
	public Integer checkRegister(HttpServletRequest request) throws Exception{
		
		
		Integer id=loginService.checkRegister(request);
			
		
		return id;
				
	}
	
	@RequestMapping("/checkLogin")
	@ResponseBody
	public ResultModel checkLogin(HttpServletRequest request) throws Exception{
		
		return loginService.checkUserIsExist(request);
		
	}
}
