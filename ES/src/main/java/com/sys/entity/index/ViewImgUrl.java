package com.sys.entity.index;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * viewImgUrl 实体类 Mon Oct 09 17:10:13 CST 2017 孙文祥
 */
@Component
@Entity
@Table(name = "view_img_url")
public class ViewImgUrl {

	/** 分享id */
	@Id
	@Column(name = "id", length = 32)
	private String id;

	/** 图片地址 */
	@Column(name = "img_url", length = 100)
	private String imgUrl;

	public ViewImgUrl() {
		super();
	}

	public ViewImgUrl(String id, String imgUrl) {
		super();
		this.id = id;
		this.imgUrl = imgUrl;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getId() {

		return id;
	}

	public void setImgUrl(String imgUrl) {

		this.imgUrl = imgUrl;
	}

	public String getImgUrl() {

		return imgUrl;
	}

	@Override
	public String toString() {
		return "ViewImgUrl [id=" + id + ", imgUrl=" + imgUrl + "]";
	}
}
