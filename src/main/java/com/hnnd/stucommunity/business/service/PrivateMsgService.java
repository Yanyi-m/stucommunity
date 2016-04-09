package com.hnnd.stucommunity.business.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnnd.stucommunity.business.dao.PrivateMsgDao;
import com.hnnd.stucommunity.business.model.PrivateMsg;
import com.hnnd.stucommunity.business.socket.EchoHandler;
import com.hnnd.stucommunity.common.base.ResultModel;

@Service
public class PrivateMsgService {
	
	@Autowired
	EchoHandler echoHandler;
	@Autowired
	PrivateMsgDao privateMsgDao;
	
	public ResultModel sendPrivateMsg(HttpServletRequest request) throws Exception{
		Integer sendId=(Integer) request.getSession().getAttribute("userId");	
		Integer recipientId=Integer.valueOf(request.getParameter("recipientId"));
		String message=request.getParameter("message");
		Integer dialogId=Integer.valueOf(request.getParameter("dialogId"));
		
		//私信生产：
		//1.发送者发送消息时，先检查dialogId是否存在。
		//若无则生成一个dialogId,代表此次消息为新建一个会话。	
		if(dialogId==null){
			dialogId=privateMsgDao.saveDialogId();
		}
	    
		//2.将消息内容存入消息内容表中，得到messageId
		Integer messageId=privateMsgDao.saveMessage(message);
		
		//3.将dialogId,messageId,senderId,recipientId存入私信表中，id_read默认为0处于未读状态。
		PrivateMsg privateMsg=new PrivateMsg();
		privateMsg.setMsgId(messageId);
		privateMsg.setSenderId(sendId);
		privateMsg.setRecipientId(recipientId);
		privateMsg.setDialogId(dialogId);
		Integer privateMsgId=privateMsgDao.savePrivateMsg(privateMsg);
		
		//如果对方当前不在线，即无法得到其ws的session，将返回false
		if(echoHandler.sendMessageToUser(privateMsg,message)){
			return ResultModel.successModel();
		}
		
		return ResultModel.failModel();
	}
}
