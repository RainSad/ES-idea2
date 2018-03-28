package com.sys.controller.webSocket.chat;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

public class MyWebSocketInterceptor implements HandshakeInterceptor{

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {

		//将ServerHttpRequest转换成request请求相关的类，用来获取request域中的用户信息
//		if(request instanceof ServletServerHttpRequest) {
//			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			//获取httpRequest
			//HttpServletRequest httpRequest = servletRequest.getServletRequest();
			//获取reauest中存储的u用户数据
			//UserInfo userInfo = (UserInfo) httpRequest.getAttribute("info");
			//从request session中获取数据
//			HttpSession session = servletRequest.getServletRequest().getSession(false);
//			UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
//			attributes.put("userInfo", userInfo);
//		}
		return true;
	}

}
