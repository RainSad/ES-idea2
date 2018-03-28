package com.core.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * sysUser 实体类 Mon Oct 09 16:32:00 CST 2017 孙文祥
 */
@Component
public class SysUserSecurity implements UserDetails{


	/**
	 * 
	 */
	private static final long serialVersionUID = 2282433542933908344L;

	/** 分享id */
	private String id;

	/** 用户名 */
	private String username;

	/***/
	private String realname;

	/** 昵称 */
	private String nickname;

	/** 密码 */
	private String password;

	/** 联系电话 */
	private String phone;

	/** 邮箱 */
	private String email;

	/** 生日 */
	private Date birthday;

	/** 性别 0不详 1男 2女 */
	private String geneder;

	/** 注册时间 */
	private Date regTime;

	/** 部门id */
	private String deptId;

	/** 1可用 0禁用 */
	private String status;

	/***/
	private String remark;
	
	List<GrantedAuthority> grantedAuthority = new ArrayList<GrantedAuthority>();

	public SysUserSecurity() {
		super();
	}

	public SysUserSecurity(String id, String username, String realname, String nickname, String password, String phone,
			String email, Date birthday, String geneder, Date regTime, String deptId, String status, String remark) {
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
		this.status = status;
		this.remark = remark;
	}

	public void setId(String id) {

		this.id = id;
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
	
	

	public List<GrantedAuthority> getGrantedAuthority() {
		return grantedAuthority;
	}

	public void setGrantedAuthority(List<GrantedAuthority> grantedAuthority) {
		this.grantedAuthority = grantedAuthority;
	}

	@Override
	public String toString() {
		return "SysUser [id=" + id + ", username=" + username + ", realname=" + realname + ", nickname=" + nickname
				+ ", password=" + password + ", phone=" + phone + ", email=" + email + ", birthday=" + birthday
				+ ", geneder=" + geneder + ", regTime=" + regTime + ", deptId=" + deptId + ", status=" + status
				+ ", remark=" + remark + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return grantedAuthority;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
