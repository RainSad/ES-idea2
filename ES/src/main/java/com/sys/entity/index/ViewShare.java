package com.sys.entity.index;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

   /**
    * viewShare 实体类
    * Fri Oct 20 18:17:11 CST 2017 孙文祥
    */ 
@Component
@Entity
@Table(name = "view_share")
public class ViewShare{

	/**分享id*/
	@Id
	@Column(name = "id", length = 32)
	private String id;

	/**分享用户id*/
	@Column(name = "user_id", length = 32)
	private String userId;

	/**创建时间*/
	@Column(name = "creat_time", length = 19)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date creatTime;

	/**点击次数*/
	@Column(name = "click_num", length = 11)
	private Integer clickNum;

	/**赞次数*/
	@Column(name = "like_num", length = 11)
	private Integer likeNum;

	/**图片地址*/
	@Column(name = "img_url_id", length = 32)
	private String imgUrlId;

	/**标题*/
	@Column(name = "title", length = 100)
	private String title;

	/**内容*/
	@Column(name = "message", length = 5000)
	private String message;

	/**状态，0为显示，1为不现实*/
	@Column(name = "status", length = 2)
	private String status;

	/**地址*/
	@Column(name = "address", length = 10)
	private String address;

	/**自评分数*/
	@Column(name = "self_evaluation", length = 3)
	private Integer selfEvaluation;

	/**游客评分*/
	@Column(name = "other_evaluation", length = 3)
	private Integer otherEvaluation;

	/**评论id*/
	@Column(name = "comment_id", length = 32)
	private String commentId;

	/**分享类型，如主页轮播图还是其他，0为主页轮播，1为推荐，2为用户一般分享*/
	@Column(name = "type", length = 2)
	private String type;

	public ViewShare(){
		super();
	}
	public ViewShare(String id, String userId, Date creatTime, Integer clickNum, Integer likeNum, String imgUrlId, String title, String message, String status, String address, Integer selfEvaluation, Integer otherEvaluation, String commentId, String type) {
		super();
		this.id = id;
		this.userId = userId;
		this.creatTime = creatTime;
		this.clickNum = clickNum;
		this.likeNum = likeNum;
		this.imgUrlId = imgUrlId;
		this.title = title;
		this.message = message;
		this.status = status;
		this.address = address;
		this.selfEvaluation = selfEvaluation;
		this.otherEvaluation = otherEvaluation;
		this.commentId = commentId;
		this.type = type;
	}
	public void setId(String id){

		this.id=id;
	}

	public String getId(){

		return id;
	}

	public void setUserId(String userId){

		this.userId=userId;
	}

	public String getUserId(){

		return userId;
	}

	public void setCreatTime(Date creatTime){

		this.creatTime=creatTime;
	}

	public Date getCreatTime(){

		return creatTime;
	}

	public void setClickNum(Integer clickNum){

		this.clickNum=clickNum;
	}

	public Integer getClickNum(){

		return clickNum;
	}

	public void setLikeNum(Integer likeNum){

		this.likeNum=likeNum;
	}

	public Integer getLikeNum(){

		return likeNum;
	}

	public void setImgUrlId(String imgUrlId){

		this.imgUrlId=imgUrlId;
	}

	public String getImgUrlId(){

		return imgUrlId;
	}

	public void setTitle(String title){

		this.title=title;
	}

	public String getTitle(){

		return title;
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

	public void setAddress(String address){

		this.address=address;
	}

	public String getAddress(){

		return address;
	}

	public void setSelfEvaluation(Integer selfEvaluation){

		this.selfEvaluation=selfEvaluation;
	}

	public Integer getSelfEvaluation(){

		return selfEvaluation;
	}

	public void setOtherEvaluation(Integer otherEvaluation){

		this.otherEvaluation=otherEvaluation;
	}

	public Integer getOtherEvaluation(){

		return otherEvaluation;
	}

	public void setCommentId(String commentId){

		this.commentId=commentId;
	}

	public String getCommentId(){

		return commentId;
	}

	public void setType(String type){

		this.type=type;
	}

	public String getType(){

		return type;
	}
	@Override
	public String toString() {
		return "ViewShare [id=" + id + ", userId=" + userId + ", creatTime=" + creatTime + ", clickNum=" + clickNum + ", likeNum=" + likeNum + ", imgUrlId=" + imgUrlId + ", title=" + title + ", message=" + message + ", status=" + status + ", address=" + address + ", selfEvaluation=" + selfEvaluation + ", otherEvaluation=" + otherEvaluation + ", commentId=" + commentId + ", type=" + type + "]";
	}
}

