package com.arn.hdss.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pregnancyobservation")
public class Pregnancyobservation {
	
	@Id
	@Column(name = "uuid", nullable = false)
	private String uuid;
	
	@Column(name = "extId", nullable = false)
	private String extId;
	
	@Column(name = "insertDate", nullable = false)
	private Date insertDate;
	
	@Column(name = "recordedDate", nullable = false)
	private Date recordedDate;
	
	@Column(name = "expectedDeliveryDate", nullable = false)
	private Date expectedDeliveryDate;
	
	@Column(name = "visitid", nullable = false)
	private String visitid;
	
	@Column(name = "outcome")
	private Integer outcome;
	
	@Column(name = "fw", nullable = false)
	private String fw;
	
	public Pregnancyobservation() {}


		
	public Pregnancyobservation(String uuid, String extId, Date insertDate, Date recordedDate,
			Date expectedDeliveryDate, String visitid, Integer outcome, String fw) {
		super();
		this.uuid = uuid;
		this.extId = extId;
		this.insertDate = insertDate;
		this.recordedDate = recordedDate;
		this.expectedDeliveryDate = expectedDeliveryDate;
		this.visitid = visitid;
		this.outcome = outcome;
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


	public Date getExpectedDeliveryDate() {
		return expectedDeliveryDate;
	}


	public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
		this.expectedDeliveryDate = expectedDeliveryDate;
	}


	public String getVisitid() {
		return visitid;
	}


	public void setVisitid(String visitid) {
		this.visitid = visitid;
	}
	
	public Integer getOutcome() {
		return outcome;
	}


	public void setOutcome(Integer outcome) {
		this.outcome = outcome;
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
