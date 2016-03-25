package com.hnnd.stucommunity.business.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.hnnd.stucommunity.business.model.UserInformation;
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
	public ModelAndView checkRegister(HttpServletRequest request) throws Exception{
		
		
		Integer id=loginService.checkRegister(request);
		if(id!=null){
			return new ModelAndView(new RedirectView("/login.html"));
		}
		
		return new ModelAndView(new RedirectView("/register.html"));
				
	}
	
	@RequestMapping("/checkLogin")
	@ResponseBody
	public ModelAndView checkLogin(HttpServletRequest request) throws Exception{
		
		if(loginService.checkUserIsExist(request).isSuccess()){
			
			return new ModelAndView(new RedirectView("/index.html"));
		}
		
		return new ModelAndView(new RedirectView("/error.html"));
	}
	
	/**
	 * 通过id拿到昵称
	 * @param request
	 * @return
	 */
	@RequestMapping("/getUser")
	@ResponseBody
	public ResultModel getUser(HttpServletRequest request){
		Integer userId=((Integer)request.getSession().getAttribute("userId"));
		
		String nickname=loginService.getUserInformation(userId).getNickName();
		
		return ResultModel.successModel(nickname);
	}
	
	/**
	 * 通过昵称字符串找到昵称
	 * @param request
	 * @return
	 */
	@RequestMapping("/getNickName")
	@ResponseBody
	public ResultModel getNickNameByName(HttpServletRequest request){
		String name=request.getParameter("nickName");
		
		List<UserInformation> list=loginService.getUserInformationByName(name);
		
		return ResultModel.successModel(list);
	}
}
