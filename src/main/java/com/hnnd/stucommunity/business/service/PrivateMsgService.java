package com.hnnd.stucommunity.business.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import com.hnnd.stucommunity.business.socket.EchoHandler;
import com.hnnd.stucommunity.common.base.ResultModel;

@Service
public class PrivateMsgService {
	
	@Autowired
	EchoHandler echoHandler;
	
	public ResultModel sendPrivateMsg(HttpServletRequest request) throws Exception{
		Integer sendId=(Integer) request.getSession().getAttribute("userId");
		
		Integer recipientId=Integer.valueOf(request.getParameter("recipientId"));
		String privateMsg=request.getParameter("message");
		
		//私信生产：
		//发送者发送消息时，第一步生成一个dialog
		
		
		//如果对方当前不在线，即无法得到其ws的session，将返回false
		if(echoHandler.sendMessageToUser(sendId,recipientId, privateMsg)){
			return ResultModel.successModel();
		}
		
		//处理
		return ResultModel.failModel();
	}
}
