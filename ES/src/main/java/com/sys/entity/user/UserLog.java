package com.sys.entity.user;

import org.springframework.stereotype.Component;

/**
 * user_log 实体类 Sun Aug 13 16:07:29 CST 2017 孙文祥
 */
@Component
public class UserLog {
	private String id;
	private String userId;
	private String loginTime;
	private String loginIp;
	private String loginAddr;
	private String endTime;
	private String browser;
	private String os;
	private String userName;

	public UserLog() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getLoginAddr() {
		return loginAddr;
	}

	public void setLoginAddr(String loginAddr) {
		this.loginAddr = loginAddr;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "UserLog [id=" + id + ", userId=" + userId + ", loginTime=" + loginTime + ", loginIp=" + loginIp
				+ ", loginAddr=" + loginAddr + ", endTime=" + endTime + ", browser=" + browser + ", os=" + os
				+ ", userName=" + userName + "]";
	}

}
