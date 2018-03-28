package com.sys.entity.user;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

   /**
    * userOperation 实体类
    * Mon Oct 09 17:10:13 CST 2017 孙文祥
    */ 
@Component
@Entity
@Table(name = "user_operation")
public class UserOperation{

	/**分享id*/
	@Id
	@Column(name = "id", length = 32)
	private String id;

	/***/
	@Column(name = "controller_method", length = 20)
	private String controllerMethod;

	/***/
	@Column(name = "click_time", length = 10)
	private String clickTime;

	public UserOperation(){
		super();
	}
	public UserOperation(String id, String controllerMethod, String clickTime) {
		super();
		this.id = id;
		this.controllerMethod = controllerMethod;
		this.clickTime = clickTime;
	}
	public void setId(String id){

		this.id=id;
	}

	public String getId(){

		return id;
	}

	public void setControllerMethod(String controllerMethod){

		this.controllerMethod=controllerMethod;
	}

	public String getControllerMethod(){

		return controllerMethod;
	}

	public void setClickTime(String clickTime){

		this.clickTime=clickTime;
	}

	public String getClickTime(){

		return clickTime;
	}
	@Override
	public String toString() {
		return "UserOperation [id=" + id + ", controllerMethod=" + controllerMethod + ", clickTime=" + clickTime + "]";
	}
}

