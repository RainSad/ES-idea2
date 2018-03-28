package com.sys.controller.webSocket.chat;

import com.sun.jersey.client.impl.CopyOnWriteHashMap;
import com.sys.entity.user.UserInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Map.Entry;

/**
 * @author 孙文祥
 * @ClassName: WebSocketPushHandler
 * @Description: 聊天信息处理类
 * @date 2017年8月18日 上午8:53:40
 */
@Component
public class WebSocketPushHandler implements WebSocketHandler {

    private static final CopyOnWriteHashMap<String, WebSocketSession> users = new CopyOnWriteHashMap<String, WebSocketSession>();
    UserInfo userInfo;

    /**
     * 用户退出后的处理 退出之后，要将用户信息从websocket的session中remove掉 这样用户就处于离线状态了，也不会占用系统资源
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeSatus) throws Exception {

        if (session.isOpen()) {
            session.close();
        }
        users.remove(userInfo.getId());
        System.out.println(userInfo.getId() + "退出系统");
    }

    /**
     * 用户进入监听
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

//		userInfo = (UserInfo) session.getAttributes().get("info");
//		users.put(userInfo.getId(), session);
//		System.out.println(userInfo.getId() + "进入系统");
        System.out.println("进入系统");
    }

    /**
     * 接收消息类，并且转发
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        // 将消息进行转化，因为是消息是json数据，可能里面包含了发送给某个人的信息，所以需要用json相关的工具类处理之后再封装成TextMessage
        // 我这儿并没有做处理，消息的封装格式一般有{from:xxxx,to:xxxxx,msg:xxxxx}，来自哪里，发送给谁，什么消息等等
        sendMessageToUser((TextMessage) message.getPayload());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable arg1) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean supportsPartialMessages() {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @param message    设定文件
     * @return void   
     * 返回类型 @throws
     * @Description: 给指定用户发送消息
     * @Title: sendMessageToUser @param @param
     * userId @param
     */
    public void sendMessageToUser(String userId, TextMessage message) {
        WebSocketSession session = users.get(userId);
        if (session.isOpen()) {
            try {
                session.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("消息未发送");
            }
        }
    }

    /**
     * @Description:给所有用户发送消息 @Title: sendMessageToUser @param @param message   
     * 设定文件 @return void    返回类型 @throws
     */
    public void sendMessageToUser(TextMessage message) {
        for (Entry<String, WebSocketSession> entry : users.entrySet()) {
            System.out.println("key= " + entry.getKey() + "and value = " + entry.getValue());
        }
    }
}
