package com.arn.hdss.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="relationship")
public class Relationship {
	
	@Id
	@Column(name = "uuid", nullable = false)
	private String uuid;
	
	@Column(name = "extId", nullable = false)
	private String extId;
	
	@Column(name = "extIdB", nullable = false)
	private String extIdB;
	
	@Column(name = "insertDate", nullable = false)
	private Date insertDate;
	
	@Column(name = "startDate", nullable = false)
	private Date startDate;
	
	@Column(name = "endDate")
	private Date endDate;
	
	@Column(name = "aIsToB", nullable = false)
	private Integer aIsToB;
	
	@Column(name = "endType", nullable = false)
	private Integer endType;
	
	@Column(name = "fw", nullable = false)
	private String fw;
	
	public Relationship() {}

	
	public Relationship(String uuid, String extId, String extIdB, Date insertDate, Date startDate, Date endDate,
			Integer aIsToB, Integer endType, String fw) {
		super();
		this.uuid = uuid;
		this.extId = extId;
		this.extIdB = extIdB;
		this.insertDate = insertDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.aIsToB = aIsToB;
		this.endType = endType;
		this.fw = fw;
	}





	public String getExtId() {
		return extId;
	}


	public void setExtId(String extId) {
		this.extId = extId;
	}


	public String getExtIdB() {
		return extIdB;
	}


	public void setExtIdB(String extIdB) {
		this.extIdB = extIdB;
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


	public Integer getaIsToB() {
		return aIsToB;
	}


	public void setaIsToB(Integer aIsToB) {
		this.aIsToB = aIsToB;
	}
	
	
	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public Integer getEndType() {
		return endType;
	}


	public void setEndType(Integer endType) {
		this.endType = endType;
	}


	public String getFw() {
		return fw;
	}


	public void setFw(String fw) {
		this.fw = fw;
	}



	@Override
	public String toString() {
		return uuid;
	}

	//@Override
    //public String toString() {
    //    return "Individual" + "extId=" + extId + ", insertDate=" + insertDate + ", dob=" + dob + ", dobAspect=" + dobAspect + '}';
    //}

}
