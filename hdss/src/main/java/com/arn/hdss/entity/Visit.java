package com.arn.hdss.entity;



import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="visit")
public class Visit {
	
	@Id
	@Column(name = "extId", nullable = false)
	private String extId;
	
	@Column(name = "insertDate", nullable = false)
	private Date insertDate;
		
	@Column(name = "realVisit", nullable = false)
	private Integer realVisit;
	
	@Column(name = "roundNumber", nullable = false)
	private Integer roundNumber;
	
	@Column(name = "visitDate", nullable = false)
	private Date visitDate;
	
	@Column(name = "loation", nullable = false)
	private String loation;
	
	@Column(name = "fw", nullable = false)
	private String fw;
	
	
	public Visit() {}

	
	public Visit(String extId, Date insertDate, Integer realVisit, Integer roundNumber, Date visitDate, String loation,
			String fw) {
		super();
		this.extId = extId;
		this.insertDate = insertDate;
		this.realVisit = realVisit;
		this.roundNumber = roundNumber;
		this.visitDate = visitDate;
		this.loation = loation;
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


	public Integer getRealVisit() {
		return realVisit;
	}


	public void setRealVisit(Integer realVisit) {
		this.realVisit = realVisit;
	}


	public Integer getRoundNumber() {
		return roundNumber;
	}


	public void setRoundNumber(Integer roundNumber) {
		this.roundNumber = roundNumber;
	}


	public Date getVisitDate() {
		return visitDate;
	}


	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}


	public String getLoation() {
		return loation;
	}


	public void setLoation(String loation) {
		this.loation = loation;
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
