package com.sys.entity.param;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Mail implements Serializable{
    public static final String ENCODEING = "UTF-8";
    // 服务器地址
    private String host;
    // 发件人的邮箱
    private String sender;
    // 发件人昵称11
    private String name;
    // 账号
    private String username;
    // 密码
    private String password;
    // 收件人的邮箱
    private String receiver;
    // 收件人的名称
    private String receiverName;
    // 收件人的邮箱(key)和名称(value)
    private Map<String, String> to;
    // 抄送人的邮箱(key)和名称(value)
    private Map<String, String> cc;
    // 秘密抄送人的邮箱(key)和名称(value)
    private Map<String, String> bcc;
    // 主题
    private String subject;
    //信息(支持HTML)
    private String message;
}
