package com.sys.entity.sys;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * sysUserInfo 实体类 Mon Oct 09 16:32:00 CST 2017 孙文祥
 */
@Component
@Entity
@Table(name = "sys_user_info")
public class SysUserInfo {

	/** 分享id */
	@Id
	@Column(name = "id", length = 32)
	private String id;

	/** 昵称 */
	@Column(name = "nickname", length = 20)
	private String nickname;

	/** 用户名 */
	@Column(name = "username", length = 20)
	private String username;

	/***/
	@Column(name = "realname", length = 20)
	private String realname;

	/** 性别 */
	@Column(name = "sex", length = 2)
	private String sex;

	/** 生日 */
	@Column(name = "birthday", length = 10)
	private String birthday;

	/** QQ */
	@Column(name = "qq", length = 15)
	private String qq;

	/** 微信 */
	@Column(name = "wechat", length = 20)
	private String wechat;

	/** 微博 */
	@Column(name = "weibo", length = 20)
	private String weibo;

	/** 活跃度 */
	@Column(name = "score", length = 10)
	private String score;

	/** 注册时间 */
	@Column(name = "reg_time", length = 10)
	private String regTime;

	/***/
	@Column(name = "reg_ip", length = 15)
	private String regIp;

	/***/
	@Column(name = "dep_id", length = 32)
	private String depId;

	/***/
	@Column(name = "remark", length = 1000)
	private String remark;

	/** 1可用 0禁用 */
	@Column(name = "status", length = 2)
	private String status;

	public SysUserInfo() {
		super();
	}

	public SysUserInfo(String id, String nickname, String username, String realname, String sex, String birthday,
			String qq, String wechat, String weibo, String score, String regTime, String regIp, String depId,
			String remark, String status) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.username = username;
		this.realname = realname;
		this.sex = sex;
		this.birthday = birthday;
		this.qq = qq;
		this.wechat = wechat;
		this.weibo = weibo;
		this.score = score;
		this.regTime = regTime;
		this.regIp = regIp;
		this.depId = depId;
		this.remark = remark;
		this.status = status;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getId() {

		return id;
	}

	public void setNickname(String nickname) {

		this.nickname = nickname;
	}

	public String getNickname() {

		return nickname;
	}

	public void setUsername(String username) {

		this.username = username;
	}

	public String getUsername() {

		return username;
	}

	public void setRealname(String realname) {

		this.realname = realname;
	}

	public String getRealname() {

		return realname;
	}

	public void setSex(String sex) {

		this.sex = sex;
	}

	public String getSex() {

		return sex;
	}

	public void setBirthday(String birthday) {

		this.birthday = birthday;
	}

	public String getBirthday() {

		return birthday;
	}

	public void setQq(String qq) {

		this.qq = qq;
	}

	public String getQq() {

		return qq;
	}

	public void setWechat(String wechat) {

		this.wechat = wechat;
	}

	public String getWechat() {

		return wechat;
	}

	public void setWeibo(String weibo) {

		this.weibo = weibo;
	}

	public String getWeibo() {

		return weibo;
	}

	public void setScore(String score) {

		this.score = score;
	}

	public String getScore() {

		return score;
	}

	public void setRegTime(String regTime) {

		this.regTime = regTime;
	}

	public String getRegTime() {

		return regTime;
	}

	public void setRegIp(String regIp) {

		this.regIp = regIp;
	}

	public String getRegIp() {

		return regIp;
	}

	public void setDepId(String depId) {

		this.depId = depId;
	}

	public String getDepId() {

		return depId;
	}

	public void setRemark(String remark) {

		this.remark = remark;
	}

	public String getRemark() {

		return remark;
	}

	public void setStatus(String status) {

		this.status = status;
	}

	public String getStatus() {

		return status;
	}

	@Override
	public String toString() {
		return "SysUserInfo [id=" + id + ", nickname=" + nickname + ", username=" + username + ", realname=" + realname
				+ ", sex=" + sex + ", birthday=" + birthday + ", qq=" + qq + ", wechat=" + wechat + ", weibo=" + weibo
				+ ", score=" + score + ", regTime=" + regTime + ", regIp=" + regIp + ", depId=" + depId + ", remark="
				+ remark + ", status=" + status + "]";
	}
}
