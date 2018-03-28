package com.sys.entity.sys;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * sysRoleAcl 实体类
 * Fri Dec 29 14:12:31 CST 2017 孙文祥
 */
@Component
@Entity
@Table(name = "sys_role_acl")
public class SysRoleAcl {

    /***/
    @Id
    @Column(name = "id", length = 32)
    private String id;

    /***/
    @Column(name = "role_id", length = 32)
    private String roleId;

    /***/
    @Column(name = "acl_id", length = 32)
    private String aclId;

    /***/
    @Column(name = "opertor", length = 32)
    private String opertor;

    /***/
    @Column(name = "operate_time", length = 19)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operateTime;

    /***/
    @Column(name = "operate_ip", length = 20)
    private String operateIp;

    public SysRoleAcl() {
        super();
    }

    public SysRoleAcl(String id, String roleId, String aclId, String opertor, Date operateTime, String operateIp) {
        super();
        this.id = id;
        this.roleId = roleId;
        this.aclId = aclId;
        this.opertor = opertor;
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

    public void setAclId(String aclId) {

        this.aclId = aclId;
    }

    public String getAclId() {

        return aclId;
    }

    public void setOpertor(String opertor) {

        this.opertor = opertor;
    }

    public String getOpertor() {

        return opertor;
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
        return "SysRoleAcl [id=" + id + ", roleId=" + roleId + ", aclId=" + aclId + ", opertor=" + opertor + ", operateTime=" + operateTime + ", operateIp=" + operateIp + "]";
    }
}

