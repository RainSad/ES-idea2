package com.sys.entity.sys;

import java.util.Date;

import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import org.springframework.stereotype.Component;

/**
 * sysAclModule 实体类
 * Fri Dec 29 14:11:10 CST 2017 孙文祥
 */
@Entity
@Table(name = "sys_acl_module")
@Builder
public class SysAclModule {

    /***/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 11)
    private String id;

    /***/
    @Column(name = "name", length = 64)
    private String name;

    /***/
    @Column(name = "parent_id", length = 32)
    private String parentId;

    /***/
    @Column(name = "level", length = 10)
    private String level;

    /***/
    @Column(name = "status", length = 2)
    private String status;

    /***/
    @Column(name = "seq", length = 32)
    private String seq;

    /***/
    @Column(name = "remark", length = 255)
    private String remark;

    /***/
    @Column(name = "operator", length = 32)
    private String operator;

    /***/
    @Column(name = "operate_time", length = 19)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operateTime;

    /***/
    @Column(name = "operate_ip", length = 255)
    private String operateIp;

    public SysAclModule() {
        super();
    }

    public SysAclModule(String id, String name, String parentId, String level, String status, String seq, String remark, String operator, Date operateTime, String operateIp) {
        super();
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.level = level;
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

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setParentId(String parentId) {

        this.parentId = parentId;
    }

    public String getParentId() {

        return parentId;
    }

    public void setLevel(String level) {

        this.level = level;
    }

    public String getLevel() {

        return level;
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
        return "SysAclModule [id=" + id + ", name=" + name + ", parentId=" + parentId + ", level=" + level + ", status=" + status + ", seq=" + seq + ", remark=" + remark + ", operator=" + operator + ", operateTime=" + operateTime + ", operateIp=" + operateIp + "]";
    }
}

