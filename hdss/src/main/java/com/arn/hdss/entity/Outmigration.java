package com.arn.hdss.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="outmigration")
public class Outmigration {
	
	@Id
	@Column(name = "uuid", nullable = false)
	private String uuid;
	
	@Column(name = "extId", nullable = false)
	private String extId;
	
	@Column(name = "insertDate", nullable = false)
	private Date insertDate;
	
	@Column(name = "recordedDate", nullable = false)
	private Date recordedDate;
	
	@Column(name = "destination", nullable = false)
	private Integer destination;
	
	@Column(name = "reason", nullable = false)
	private Integer reason;
	
	@Column(name = "visitid", nullable = false)
	private String visitid;
	
	@Column(name = "fw", nullable = false)
	private String fw;
	
	public Outmigration() {}


	public Outmigration(String uuid, String extId, Date insertDate, Date recordedDate, Integer destination,
			Integer reason, String visitid, String fw) {
		super();
		this.uuid = uuid;
		this.extId = extId;
		this.insertDate = insertDate;
		this.recordedDate = recordedDate;
		this.destination = destination;
		this.reason = reason;
		this.visitid = visitid;
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


	public Date getRecordedDate() {
		return recordedDate;
	}


	public void setRecordedDate(Date recordedDate) {
		this.recordedDate = recordedDate;
	}


	public Integer getDestination() {
		return destination;
	}


	public void setDestination(Integer destination) {
		this.destination = destination;
	}


	public Integer getReason() {
		return reason;
	}


	public void setReason(Integer reason) {
		this.reason = reason;
	}


	public String getVisitid() {
		return visitid;
	}


	public void setVisitid(String visitid) {
		this.visitid = visitid;
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



	//@Override
    //public String toString() {
    //    return "Individual" + "extId=" + extId + ", insertDate=" + insertDate + ", dob=" + dob + ", dobAspect=" + dobAspect + '}';
    //}

}
