package com.hnnd.stucommunity.business.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnnd.stucommunity.business.service.CommunityService;
import com.hnnd.stucommunity.common.base.ResultModel;

@Controller
public class CommunityController {
	
	@Autowired
	CommunityService communityService;
	
	@RequestMapping("/saveCommunity")
	@ResponseBody
	public ResultModel saveCommunity(HttpServletRequest request){
		
		
		return ResultModel.successModel();
	}
}
