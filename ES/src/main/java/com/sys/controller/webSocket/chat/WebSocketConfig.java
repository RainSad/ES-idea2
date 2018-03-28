package com.sys.controller.webSocket.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * 
 * @Description: 这一个类似于servlet项目中的@ServerEndpoint注解，
 *               它的作用就是在html页面中可以直接访问这个类配置的网址，从而进行相关逻辑的处理，
 *               而不是经过controller层来管理，有了这个类，WebSocketConfig就相当于是controller层了
 * @ClassName: WebSocketConfig
 * @author 孙文祥
 * @date 2017年8月18日 上午9:15:24
 *
 */
@Component
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

	@Autowired
	private WebSocketPushHandler webSocketPushHandler;
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

		registry.addHandler(webSocketPushHandler,"webSocketServer/chat").addInterceptors(new MyWebSocketInterceptor());
		registry.addHandler(webSocketPushHandler,"sockjs/webSocketServer/chat").addInterceptors(new MyWebSocketInterceptor()).withSockJS();
	}

	
//	private WebSocketHandler webSocketPushHandler() {
//		return new WebSocketPushHandler();
//	}

}
