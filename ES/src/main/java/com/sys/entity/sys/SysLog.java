package com.sys.entity.sys;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * sysLog 实体类
 * Fri Dec 29 14:12:03 CST 2017 孙文祥
 */
@Component
@Entity
@Table(name = "sys_log")
public class SysLog {

    /***/
    @Id
    @Column(name = "id", length = 32)
	private String id;

	/**ip地址*/
    @Column(name = "ip", length = 32)
	private String ip;

	/**操作系统*/
    @Column(name = "os", length = 64)
	private String os;

	/**浏览器*/
    @Column(name = "browser", length = 64)
	private String browser;

	/**用户名*/
    @Column(name = "opt_user", length = 32)
	private String optUser;

	/**用户姓名*/
    @Column(name = "realname", length = 32)
	private String realname;

	/**是否成功 1是 0否*/
    @Column(name = "is_success", length = 2)
	private String isSuccess;

	/**请求地址*/
    @Column(name = "req_url", length = 64)
	private String reqUrl;

	/***/
	@Column(name = "login_addr", length = 20)
	private String loginAddr;

	/***/
    @Column(name = "create_time", length = 19)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

    /**描述*/
    @Column(name = "remark", length = 255)
    private String remark;

    /***/
    @Column(name = "type", length = 2)
    private String type;

    /***/
    @Column(name = "target_id", length = 32)
    private String targetId;

    /***/
    @Column(name = "old_value", length = 255)
    private String oldValue;

    /***/
    @Column(name = "new_value", length = 255)
    private String newValue;

    /***/
    @Column(name = "operator", length = 20)
    private String operator;

    /***/
    @Column(name = "operate_time", length = 19)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operateTime;

    /***/
    @Column(name = "operate_ip", length = 20)
    private String operateIp;

    public SysLog() {
        super();
	}

    public SysLog(String id, String ip, String os, String browser, String optUser, String realname, String isSuccess, String reqUrl, String loginAddr, Date createTime, String remark, String type, String targetId, String oldValue, String newValue, String operator, Date operateTime, String operateIp) {
        super();
		this.id = id;
		this.ip = ip;
		this.os = os;
		this.browser = browser;
		this.optUser = optUser;
		this.realname = realname;
		this.isSuccess = isSuccess;
		this.reqUrl = reqUrl;
		this.loginAddr = loginAddr;
		this.createTime = createTime;
		this.remark = remark;
        this.type = type;
        this.targetId = targetId;
        this.oldValue = oldValue;
        this.newValue = newValue;
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

	public void setIp(String ip) {

        this.ip = ip;
    }

    public String getIp() {

        return ip;
	}

	public void setOs(String os) {

        this.os = os;
    }

    public String getOs() {

        return os;
	}

	public void setBrowser(String browser) {

        this.browser = browser;
    }

    public String getBrowser() {

        return browser;
	}

	public void setOptUser(String optUser) {

        this.optUser = optUser;
    }

    public String getOptUser() {

        return optUser;
	}

	public void setRealname(String realname) {

        this.realname = realname;
    }

    public String getRealname() {

        return realname;
	}

	public void setIsSuccess(String isSuccess) {

        this.isSuccess = isSuccess;
    }

    public String getIsSuccess() {

        return isSuccess;
	}

	public void setReqUrl(String reqUrl) {

        this.reqUrl = reqUrl;
    }

    public String getReqUrl() {

        return reqUrl;
	}

	public void setLoginAddr(String loginAddr) {

        this.loginAddr = loginAddr;
    }

    public String getLoginAddr() {

        return loginAddr;
	}

	public void setCreateTime(Date createTime) {

        this.createTime = createTime;
    }

    public Date getCreateTime() {

        return createTime;
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

    public void setTargetId(String targetId) {

        this.targetId = targetId;
    }

    public String getTargetId() {

        return targetId;
    }

    public void setOldValue(String oldValue) {

        this.oldValue = oldValue;
    }

    public String getOldValue() {

        return oldValue;
    }

    public void setNewValue(String newValue) {

        this.newValue = newValue;
    }

    public String getNewValue() {

        return newValue;
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
        return "SysLog [id=" + id + ", ip=" + ip + ", os=" + os + ", browser=" + browser + ", optUser=" + optUser + ", realname=" + realname + ", isSuccess=" + isSuccess + ", reqUrl=" + reqUrl + ", loginAddr=" + loginAddr + ", createTime=" + createTime + ", remark=" + remark + ", type=" + type + ", targetId=" + targetId + ", oldValue=" + oldValue + ", newValue=" + newValue + ", operator=" + operator + ", operateTime=" + operateTime + ", operateIp=" + operateIp + "]";
    }
}

