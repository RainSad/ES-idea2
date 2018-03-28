package com.sys.entity.sys;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * sysRole 实体类
 * Fri Dec 29 14:12:16 CST 2017 孙文祥
 */
@Component
@Entity
@Table(name = "sys_role")
public class SysRole {

    /***/
    @Id
    @Column(name = "id", length = 32)
	private String id;

	/**角色名称*/
    @Column(name = "role_name", length = 64)
	private String roleName;

	/**角色code*/
    @Column(name = "role_code", length = 64)
	private String roleCode;

	/**插件时间*/
    @Column(name = "create_time", length = 19)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**状态 1可用 0禁用*/
    @Column(name = "status", length = 2)
	private String status;

	/***/
	@Column(name = "remark", length = 255)
	private String remark;

	/***/
    @Column(name = "type", length = 2)
    private String type;

    /***/
    @Column(name = "operator", length = 20)
    private String operator;

    /***/
    @Column(name = "operate_ip", length = 20)
    private String operateIp;

    /***/
    @Column(name = "operate_time", length = 19)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operateTime;

    public SysRole() {
        super();
	}

    public SysRole(String id, String roleName, String roleCode, Date createTime, String status, String remark, String type, String operator, String operateIp, Date operateTime) {
        super();
		this.id = id;
		this.roleName = roleName;
		this.roleCode = roleCode;
		this.createTime = createTime;
		this.status = status;
		this.remark = remark;
        this.type = type;
        this.operator = operator;
        this.operateIp = operateIp;
        this.operateTime = operateTime;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getId() {

        return id;
	}

	public void setRoleName(String roleName) {

        this.roleName = roleName;
    }

    public String getRoleName() {

        return roleName;
	}

	public void setRoleCode(String roleCode) {

        this.roleCode = roleCode;
    }

    public String getRoleCode() {

        return roleCode;
	}

	public void setCreateTime(Date createTime) {

        this.createTime = createTime;
    }

    public Date getCreateTime() {

        return createTime;
	}

	public void setStatus(String status) {

        this.status = status;
    }

    public String getStatus() {

        return status;
	}

	public void setRemark(String remark) {

        this.remark = remark;
    }

    public String getRemark() {

        return remark;
	}

    public void setType(String type) {

        this.type = type;
    }

    public String getType() {

        return type;
    }

    public void setOperator(String operator) {

        this.operator = operator;
    }

    public String getOperator() {

        return operator;
    }

    public void setOperateIp(String operateIp) {

        this.operateIp = operateIp;
    }

    public String getOperateIp() {

        return operateIp;
    }

    public void setOperateTime(Date operateTime) {

        this.operateTime = operateTime;
    }

    public Date getOperateTime() {

        return operateTime;
    }

    @Override
	public String toString() {
        return "SysRole [id=" + id + ", roleName=" + roleName + ", roleCode=" + roleCode + ", createTime=" + createTime + ", status=" + status + ", remark=" + remark + ", type=" + type + ", operator=" + operator + ", operateIp=" + operateIp + ", operateTime=" + operateTime + "]";
    }
}

