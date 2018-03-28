package com.sys.entity.sys;

import java.util.Date;

import lombok.Builder;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import org.springframework.stereotype.Component;

/**
 * sysUser 实体类
 * Fri Dec 29 14:13:29 CST 2017 孙文祥
 */
@Builder
@Entity
@Table(name = "sys_user")
public class SysUser {

    /***/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 32)
    private String id;

	/**用户名*/
    @Column(name = "username", length = 64)
	private String username;

	/**真实姓名*/
    @Column(name = "realname", length = 64)
	private String realname;

	/**昵称*/
    @Column(name = "nickname", length = 64)
	private String nickname;

	/**密码*/
    @Column(name = "password", length = 255)
	private String password;

	/**联系电话*/
    @Column(name = "phone", length = 16)
	private String phone;

	/**邮箱*/
    @Column(name = "email", length = 64)
	private String email;

	/**生日*/
    @Column(name = "birthday", length = 19)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date birthday;

	/**性别 0不详 1男  2女*/
    @Column(name = "geneder", length = 2)
	private String geneder;

	/**注册时间*/
    @Column(name = "reg_time", length = 19)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date regTime;

	/**部门id*/
    @Column(name = "dept_id", length = 32)
	private String deptId;

	/**描述*/
    @Column(name = "remark", length = 255)
	private String remark;

	/**1正常   0禁用 2删除*/
    @Column(name = "status", length = 2)
    private String status;

    /**
     * 更新着
     */
    @Column(name = "operator", length = 64)
    private String operator;

    /**
     * 最后一次更新的时间
     */
    @Column(name = "operate_time", length = 19)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operateTime;

    /**
     * 最后一次更新的ip
     */
    @Column(name = "operate_ip", length = 20)
    private String operateIp;

    public SysUser() {
        super();
    }

    public SysUser(String id, String username, String realname, String nickname, String password, String phone, String email, Date birthday, String geneder, Date regTime, String deptId, String remark, String status, String operator, Date operateTime, String operateIp) {
        super();
		this.id = id;
		this.username = username;
		this.realname = realname;
		this.nickname = nickname;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.birthday = birthday;
		this.geneder = geneder;
		this.regTime = regTime;
		this.deptId = deptId;
        this.remark = remark;
        this.status = status;
        this.operator = operator;
        this.operateTime = operateTime;
        this.operateIp = operateIp;
    }

    public void setId(String id) {

        this.id=id;
    }

	public String getId() {

        return id;
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

    public void setNickname(String nickname) {

        this.nickname = nickname;
    }

	public String getNickname() {

        return nickname;
    }

    public void setPassword(String password) {

        this.password = password;
    }

	public String getPassword() {

        return password;
    }

    public void setPhone(String phone) {

        this.phone = phone;
    }

	public String getPhone() {

        return phone;
    }

    public void setEmail(String email) {

        this.email = email;
    }

	public String getEmail() {

        return email;
    }

    public void setBirthday(Date birthday) {

        this.birthday = birthday;
    }

	public Date getBirthday() {

        return birthday;
    }

    public void setGeneder(String geneder) {

        this.geneder = geneder;
    }

	public String getGeneder() {

        return geneder;
    }

    public void setRegTime(Date regTime) {

        this.regTime = regTime;
    }

	public Date getRegTime() {

        return regTime;
    }

    public void setDeptId(String deptId) {

        this.deptId = deptId;
    }

	public String getDeptId() {

        return deptId;
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

    public String getOperateIp(){

        return operateIp;
    }

    @Override
    public String toString() {
        return "SysUser [id=" + id + ", username=" + username + ", realname=" + realname + ", nickname=" + nickname + ", password=" + password + ", phone=" + phone + ", email=" + email + ", birthday=" + birthday + ", geneder=" + geneder + ", regTime=" + regTime + ", deptId=" + deptId + ", remark=" + remark + ", status=" + status + ", operator=" + operator + ", operateTime=" + operateTime + ", operateIp=" + operateIp + "]";
    }
}

