package com.hnnd.stucommunity.business.socket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.hnnd.stucommunity.common.dictionary.Constant;

	

public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {

	private static Logger log = LoggerFactory.getLogger(WebSocketHandshakeInterceptor.class);
	
	@Override
	public boolean beforeHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest().getSession(false);
			if (session != null) {
			//使用userId区分WebSocketHandler，以便定向发送消息
			Integer userId = (Integer) session.getAttribute(Constant.WEBSOCKET_SESSION_USERID);
			attributes.put(Constant.WEBSOCKET_SESSION_USERID,userId);
			}
			}
			return true;
	}

	@Override
	public void afterHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
		// TODO Auto-generated method stub

	}

}
