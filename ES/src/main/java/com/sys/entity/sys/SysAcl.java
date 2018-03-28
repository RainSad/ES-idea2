package com.sys.entity.sys;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * sysAcl 实体类
 * Fri Dec 29 14:10:19 CST 2017 孙文祥
 */
@Component
@Entity
@Table(name = "sys_acl")
public class SysAcl {

    /***/
    @Id
    @Column(name = "id", length = 32)
    private String id;

    /***/
    @Column(name = "code", length = 64)
    private String code;

    /***/
    @Column(name = "name", length = 32)
    private String name;

    /***/
    @Column(name = "acl_module_id", length = 32)
    private String aclModuleId;

    /***/
    @Column(name = "url", length = 64)
    private String url;

    /***/
    @Column(name = "type", length = 2)
    private String type;

    /***/
    @Column(name = "status", length = 2)
    private String status;

    /***/
    @Column(name = "seq", length = 20)
    private String seq;

    /***/
    @Column(name = "remark", length = 255)
    private String remark;

    /***/
    @Column(name = "operator", length = 64)
    private String operator;

    /***/
    @Column(name = "operate_time", length = 19)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operateTime;

    /***/
    @Column(name = "operate_ip", length = 64)
    private String operateIp;

    public SysAcl() {
        super();
    }

    public SysAcl(String id, String code, String name, String aclModuleId, String url, String type, String status, String seq, String remark, String operator, Date operateTime, String operateIp) {
        super();
        this.id = id;
        this.code = code;
        this.name = name;
        this.aclModuleId = aclModuleId;
        this.url = url;
        this.type = type;
        this.status = status;
        this.seq = seq;
        this.remark = remark;
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

    public void setCode(String code) {

        this.code = code;
    }

    public String getCode() {

        return code;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setAclModuleId(String aclModuleId) {

        this.aclModuleId = aclModuleId;
    }

    public String getAclModuleId() {

        return aclModuleId;
    }

    public void setUrl(String url) {

        this.url = url;
    }

    public String getUrl() {

        return url;
    }

    public void setType(String type) {

        this.type = type;
    }

    public String getType() {

        return type;
    }

    public void setStatus(String status) {

        this.status = status;
    }

    public String getStatus() {

        return status;
    }

    public void setSeq(String seq) {

        this.seq = seq;
    }

    public String getSeq() {

        return seq;
    }

    public void setRemark(String remark) {

        this.remark = remark;
    }

    public String getRemark() {

        return remark;
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
        return "SysAcl [id=" + id + ", code=" + code + ", name=" + name + ", aclModuleId=" + aclModuleId + ", url=" + url + ", type=" + type + ", status=" + status + ", seq=" + seq + ", remark=" + remark + ", operator=" + operator + ", operateTime=" + operateTime + ", operateIp=" + operateIp + "]";
    }
}

