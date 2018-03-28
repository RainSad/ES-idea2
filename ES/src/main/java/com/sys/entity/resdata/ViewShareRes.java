package com.sys.entity.resdata;

import java.util.Date;
import java.util.List;


public class ViewShareRes {

    /**
     * 分享id
     */
    private String id;

    /**
     * 分享用户id
     */
    private String userId;

    /**
     * 创建时间
     */
    private Date creatTime;

    /**
     * 点击次数
     */
    private Integer clickNum;

    /**
     * 赞次数
     */
    private Integer likeNum;

    /**
     * 图片地址
     */
    private List imgUrlId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String message;

    /**
     * 状态，0为显示，1为不现实
     */
    private String status;

    /**
     * 地址
     */
    private String address;

    /**
     * 自评分数
     */
    private Integer selfEvaluation;

    /**
     * 游客评分
     */
    private Integer otherEvaluation;

    /**
     * 评论id
     */
    private List commentId;

    /**
     * 分享类型，如主页轮播图还是其他，0为主页轮播，1为推荐，2为用户一般分享
     */
    private String type;


    public ViewShareRes() {
        super();
    }


    public ViewShareRes(String id, String userId, Date creatTime, Integer clickNum, Integer likeNum, List imgUrlId,
                        String title, String message, String status, String address, Integer selfEvaluation,
                        Integer otherEvaluation, List commentId, String type) {
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


    public Date getCreatTime() {
        return creatTime;
    }


    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }


    public Integer getClickNum() {
        return clickNum;
    }


    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }


    public Integer getLikeNum() {
        return likeNum;
    }


    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }


    public List getImgUrlId() {
        return imgUrlId;
    }


    public void setImgUrlId(List imgUrlId) {
        this.imgUrlId = imgUrlId;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public Integer getSelfEvaluation() {
        return selfEvaluation;
    }


    public void setSelfEvaluation(Integer selfEvaluation) {
        this.selfEvaluation = selfEvaluation;
    }


    public Integer getOtherEvaluation() {
        return otherEvaluation;
    }


    public void setOtherEvaluation(Integer otherEvaluation) {
        this.otherEvaluation = otherEvaluation;
    }


    public List getCommentId() {
        return commentId;
    }


    public void setCommentId(List commentId) {
        this.commentId = commentId;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "ViewShareRes [id=" + id + ", userId=" + userId + ", creatTime=" + creatTime + ", clickNum=" + clickNum
                + ", likeNum=" + likeNum + ", imgUrlId=" + imgUrlId + ", title=" + title + ", message=" + message
                + ", status=" + status + ", address=" + address + ", selfEvaluation=" + selfEvaluation
                + ", otherEvaluation=" + otherEvaluation + ", commentId=" + commentId + ", type=" + type + "]";
    }


}


