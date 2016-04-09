package com.hnnd.stucommunity.business.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnnd.stucommunity.business.service.PrivateMsgService;

@Controller
public class PrivateMsgController {
	
	@Autowired
	PrivateMsgService privateMsgService;
	
	/**
	 * 发送私信
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("/sendPrivateMsg")
	@ResponseBody
	public void sendPrivateMsg(HttpServletRequest request) throws Exception{
		
		
		privateMsgService.sendPrivateMsg(request);
			
	}
}
