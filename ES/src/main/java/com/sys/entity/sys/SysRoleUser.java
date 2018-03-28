package com.sys.entity.sys;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * sysRoleUser 实体类
 * Fri Dec 29 14:23:38 CST 2017 孙文祥
 */
@Component
@Entity
@Table(name = "sys_role_user")
public class SysRoleUser {

    /***/
    @Id
    @Column(name = "id", length = 32)
    private String id;

    /***/
    @Column(name = "role_id", length = 32)
    private String roleId;

    /***/
    @Column(name = "user_id", length = 32)
    private String userId;

    /***/
    @Column(name = "operator", length = 32)
    private String operator;

    /***/
    @Column(name = "operate_time", length = 19)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operateTime;

    /***/
    @Column(name = "operate_ip", length = 20)
    private String operateIp;

    public SysRoleUser() {
        super();
    }

    public SysRoleUser(String id, String roleId, String userId, String operator, Date operateTime, String operateIp) {
        super();
        this.id = id;
        this.roleId = roleId;
        this.userId = userId;
        this.operator = operator;
        this.operateTime = operateTime;
        this.operateIp = operateIp;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getId() {

        return id;
    }

    public void setRoleId(String roleId) {

        this.roleId = roleId;
    }

    public String getRoleId() {

        return roleId;
    }

    public void setUserId(String userId) {

        this.userId = userId;
    }

    public String getUserId() {

        return userId;
    }

    public void setOperator(String operator) {

        this.operator = operator;
    }

    public String getOperator() {

        return operator;
    }

    public void setOperateTime(Date operateTime) {

        this.operateTime = operateTime;
    }

    public Date getOperateTime() {

        return operateTime;
    }

    public void setOperateIp(String operateIp) {

        this.operateIp = operateIp;
    }

    public String getOperateIp() {

        return operateIp;
    }

    @Override
    public String toString() {
        return "SysRoleUser [id=" + id + ", roleId=" + roleId + ", userId=" + userId + ", operator=" + operator + ", operateTime=" + operateTime + ", operateIp=" + operateIp + "]";
    }
}

