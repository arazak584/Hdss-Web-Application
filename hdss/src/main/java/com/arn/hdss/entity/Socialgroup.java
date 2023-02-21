package com.arn.hdss.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name="socialgroup")
public class Socialgroup {
	
	@Id
	@Column(name = "extId", nullable = false)
	private String extId;
	
	@Column(name = "insertDate", nullable = false)
	private Date insertDate;
	
	
	@Column(name = "groupName", nullable = false)
	private String groupName;
	
	@Column(name = "groupType", nullable = false)
	private Integer groupType;
	
	@Column(name = "headid", nullable = false)
	private String headid;
	
	@Column(name = "fw", nullable = false)
	private String fw;
	
	public Socialgroup() {}

	

	public Socialgroup(String extId, Date insertDate, String groupName, Integer groupType,
			String headid, String fw) {
		super();
		this.extId = extId;
		this.insertDate = insertDate;
		this.groupName = groupName;
		this.groupType = groupType;
		this.headid = headid;
		this.fw = fw;
	}



	public String getExtId() {
		return extId;
	}



	public void setExtId(String extId) {
		this.extId = extId;
	}



	public Date getInsertDate() {
		return insertDate;
	}



	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}



	public String getGroupName() {
		return groupName;
	}



	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}



	public Integer getGroupType() {
		return groupType;
	}



	public void setGroupType(Integer groupType) {
		this.groupType = groupType;
	}



	public String getHeadid() {
		return headid;
	}



	public void setHeadid(String headid) {
		this.headid = headid;
	}



	public String getFw() {
		return fw;
	}



	public void setFw(String fw) {
		this.fw = fw;
	}

	@Override
	public String toString() {
		return extId;
	}

}
