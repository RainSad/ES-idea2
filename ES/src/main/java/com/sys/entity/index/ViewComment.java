package com.sys.entity.index;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

   /**
    * viewComment 实体类
    * Fri Oct 20 17:12:24 CST 2017 孙文祥
    */ 
@Component
@Entity
@Table(name = "view_comment")
public class ViewComment{

	/**id*/
	@Id
	@Column(name = "id", length = 32)
	private String id;

	/**创建时间*/
	@Column(name = "create_time", length = 19)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**评论内容*/
	@Column(name = "message", length = 5000)
	private String message;

	/**状态，是否显示，0为显示，1为不显示*/
	@Column(name = "status", length = 2)
	private String status;

	/***/
	@Column(name = "user_id", length = 32)
	private String userId;

	public ViewComment(){
		super();
	}
	public ViewComment(String id, Date createTime, String message, String status, String userId) {
		super();
		this.id = id;
		this.createTime = createTime;
		this.message = message;
		this.status = status;
		this.userId = userId;
	}
	public void setId(String id){

		this.id=id;
	}

	public String getId(){

		return id;
	}

	public void setCreateTime(Date createTime){

		this.createTime=createTime;
	}

	public Date getCreateTime(){

		return createTime;
	}

	public void setMessage(String message){

		this.message=message;
	}

	public String getMessage(){

		return message;
	}

	public void setStatus(String status){

		this.status=status;
	}

	public String getStatus(){

		return status;
	}

	public void setUserId(String userId){

		this.userId=userId;
	}

	public String getUserId(){

		return userId;
	}
	@Override
	public String toString() {
		return "ViewComment [id=" + id + ", createTime=" + createTime + ", message=" + message + ", status=" + status + ", userId=" + userId + "]";
	}
}

