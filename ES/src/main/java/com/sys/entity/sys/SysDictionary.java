package com.sys.entity.sys;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * sysDictionary 实体类 Mon Oct 09 16:32:00 CST 2017 孙文祥
 */
@Component
@Entity
@Table(name = "sys_dictionary")
public class SysDictionary {

	/** 分享id */
	@Id
	@Column(name = "id", length = 32)
	private String id;

	/** 标签 */
	@Column(name = "label", length = 64)
	private String label;

	/** 值 */
	@Column(name = "value", length = 64)
	private String value;

	/***/
	@Column(name = "parent_id", length = 32)
	private String parentId;

	/** 1菜单 2 权限 */
	@Column(name = "type", length = 128)
	private String type;

	/** 1可用 0禁用 */
	@Column(name = "status", length = 2)
	private String status;

	/***/
	@Column(name = "remark", length = 255)
	private String remark;

	public SysDictionary() {
		super();
	}

	public SysDictionary(String id, String label, String value, String parentId, String type, String status,
			String remark) {
		super();
		this.id = id;
		this.label = label;
		this.value = value;
		this.parentId = parentId;
		this.type = type;
		this.status = status;
		this.remark = remark;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getId() {

		return id;
	}

	public void setLabel(String label) {

		this.label = label;
	}

	public String getLabel() {

		return label;
	}

	public void setValue(String value) {

		this.value = value;
	}

	public String getValue() {

		return value;
	}

	public void setParentId(String parentId) {

		this.parentId = parentId;
	}

	public String getParentId() {

		return parentId;
	}

	public void setType(String type) {

		this.type = type;
	}

	public String getType() {

		return type;
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

	@Override
	public String toString() {
		return "SysDictionary [id=" + id + ", label=" + label + ", value=" + value + ", parentId=" + parentId
				+ ", type=" + type + ", status=" + status + ", remark=" + remark + "]";
	}
}
