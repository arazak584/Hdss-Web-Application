package com.arn.hdss.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name="residency")
public class Residency {
	
	@Id
	@Column(name = "uuid", nullable = false)
	private String uuid;
	
	@Column(name = "extId", nullable = false)
	private String extId;
	
	@Column(name = "insertDate", nullable = false)
	private Date insertDate;
	
	@Column(name = "startDate", nullable = false)
	private Date startDate;
	
	@Column(name = "endDate")
	private Date endDate;
	
	@Column(name = "startType", nullable = false)
	private Integer startType;
	
	@Column(name = "endType", nullable = false)
	private Integer endType;
	
	@Column(name = "location", nullable = false)
	private String location;
	
	@Column(name = "compno", nullable = false)
	private String compno;
	
	@Column(name = "socialgroup", nullable = false)
	private String socialgroup;
	
	@Column(name = "fw", nullable = false)
	private String fw;
	
	public Residency() {}

	

	public Residency(String uuid, String extId, Date insertDate, Date startDate, Date endDate, Integer startType,
			Integer endType, String location,String compno, String socialgroup, String fw) {
		super();
		this.uuid = uuid;
		this.extId = extId;
		this.insertDate = insertDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startType = startType;
		this.endType = endType;
		this.location = location;
		this.compno = compno;
		this.socialgroup = socialgroup;
		this.fw = fw;
	}



	
	public String getUuid() {
		return uuid;
	}



	public void setUuid(String uuid) {
		this.uuid = uuid;
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



	public Date getStartDate() {
		return startDate;
	}



	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}



	public Date getEndDate() {
		return endDate;
	}



	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}



	public Integer getStartType() {
		return startType;
	}



	public void setStartType(Integer startType) {
		this.startType = startType;
	}



	public Integer getEndType() {
		return endType;
	}



	public void setEndType(Integer endType) {
		this.endType = endType;
	}



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}

	
	public String getCompno() {
		return compno;
	}



	public void setCompno(String compno) {
		this.compno = compno;
	}



	public String getSocialgroup() {
		return socialgroup;
	}



	public void setSocialgroup(String socialgroup) {
		this.socialgroup = socialgroup;
	}



	public String getFw() {
		return fw;
	}



	public void setFw(String fw) {
		this.fw = fw;
	}



	//@Override
    //public String toString() {
    //    return "Individual" + "extId=" + extId + ", insertDate=" + insertDate + ", dob=" + dob + ", dobAspect=" + dobAspect + '}';
    //}

}
