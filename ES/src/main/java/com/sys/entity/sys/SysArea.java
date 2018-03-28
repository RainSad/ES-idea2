package com.sys.entity.sys;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * sysArea 实体类 Mon Oct 09 16:32:00 CST 2017 孙文祥
 */
@Component
@Entity
@Table(name = "sys_area")
public class SysArea {

	/** 分享id */
	@Id
	@Column(name = "id", length = 32)
	private String id;

	/** 城市code */
	@Column(name = "area_code", length = 16)
	private String areaCode;

	/** 区域名称 */
	@Column(name = "area_name", length = 128)
	private String areaName;

	/***/
	@Column(name = "parent_id", length = 255)
	private String parentId;

	/** 简称 */
	@Column(name = "short_name", length = 32)
	private String shortName;

	/** 经度 */
	@Column(name = "lng", length = 64)
	private String lng;

	/** 维度 */
	@Column(name = "lat", length = 64)
	private String lat;

	/** 资源级别 */
	@Column(name = "level", length = 8)
	private int level;

	/** 排序 */
	@Column(name = "sort", length = 8)
	private int sort;

	/** 1可用 0禁用 */
	@Column(name = "status", length = 2)
	private String status;

	public SysArea() {
		super();
	}

	public SysArea(String id, String areaCode, String areaName, String parentId, String shortName, String lng,
			String lat, int level, int sort, String status) {
		super();
		this.id = id;
		this.areaCode = areaCode;
		this.areaName = areaName;
		this.parentId = parentId;
		this.shortName = shortName;
		this.lng = lng;
		this.lat = lat;
		this.level = level;
		this.sort = sort;
		this.status = status;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getId() {

		return id;
	}

	public void setAreaCode(String areaCode) {

		this.areaCode = areaCode;
	}

	public String getAreaCode() {

		return areaCode;
	}

	public void setAreaName(String areaName) {

		this.areaName = areaName;
	}

	public String getAreaName() {

		return areaName;
	}

	public void setParentId(String parentId) {

		this.parentId = parentId;
	}

	public String getParentId() {

		return parentId;
	}

	public void setShortName(String shortName) {

		this.shortName = shortName;
	}

	public String getShortName() {

		return shortName;
	}

	public void setLng(String lng) {

		this.lng = lng;
	}

	public String getLng() {

		return lng;
	}

	public void setLat(String lat) {

		this.lat = lat;
	}

	public String getLat() {

		return lat;
	}

	public void setLevel(int level) {

		this.level = level;
	}

	public int getLevel() {

		return level;
	}

	public void setSort(int sort) {

		this.sort = sort;
	}

	public int getSort() {

		return sort;
	}

	public void setStatus(String status) {

		this.status = status;
	}

	public String getStatus() {

		return status;
	}

	@Override
	public String toString() {
		return "SysArea [id=" + id + ", areaCode=" + areaCode + ", areaName=" + areaName + ", parentId=" + parentId
				+ ", shortName=" + shortName + ", lng=" + lng + ", lat=" + lat + ", level=" + level + ", sort=" + sort
				+ ", status=" + status + "]";
	}
}
