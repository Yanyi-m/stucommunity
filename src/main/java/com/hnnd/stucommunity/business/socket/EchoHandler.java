package com.hnnd.stucommunity.business.socket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.hnnd.stucommunity.business.model.PrivateMsg;
import com.hnnd.stucommunity.common.dictionary.Constant;

@Component
public class EchoHandler extends TextWebSocketHandler {
	private static Logger log = LoggerFactory.getLogger(EchoHandler.class);

    private static Map<Object, WebSocketSession> sessionMap = new ConcurrentHashMap<Object, WebSocketSession>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {	
    	Integer userId=(Integer)session.getAttributes().get(Constant.WEBSOCKET_SESSION_USERID);
        log.info("established: {}", userId);
        sessionMap.put(userId, session);
    }
    

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    	Integer userId=(Integer)session.getAttributes().get(Constant.WEBSOCKET_SESSION_USERID);
        log.info("closed: {}", userId);
        sessionMap.remove(userId);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
    	Integer userId=(Integer)session.getAttributes().get(Constant.WEBSOCKET_SESSION_USERID);
        log.info("message(from={}): {}",userId, textMessage.getPayload());
        /*for (WebSocketSession sess : sessionMap.values()) {
            sess.sendMessage(textMessage);
        }*/
    }
    
    /**
    * 给某个用户发送消息
    *
    * @param userId
    * @param message
     * @throws Exception 
    */
    public boolean sendMessageToUser(PrivateMsg privateMsg,String message) throws Exception {
    	Integer senderId=privateMsg.getSenderId();
    	Integer recipientId=privateMsg.getRecipientId();
    	//找到对应的用户的websocket session
    		WebSocketSession sender = sessionMap.get(senderId);//需要判断是否有get不到的情况
    		WebSocketSession recipient;
    		
    		//发送消息
    		if (sender.isOpen()) {
    			sender.sendMessage(new TextMessage("我:"+message));
    		}else{
    			return false;
    		}	
    		
    		if(sessionMap.get(recipientId)!=null){
    			recipient = sessionMap.get(recipientId);
    		}else{
    			return false;
    		}
    		
    		if (recipient.isOpen()) {
    			recipient.sendMessage(new TextMessage(message));
    		}else{
    			return false;
    		}	
    		return true;
    }
}
